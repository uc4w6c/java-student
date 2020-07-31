package jp.co.example;


import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import jp.co.example.annotations.InvokeLog;
import jp.co.example.annotations.RequestScoped;

/**
 * @author naoki
 */
public class Context {

    static Map<String, Class> types = new HashMap<>();
    static Map<String, Object> beans = new HashMap<>();
    static ThreadLocal<Map<String, Object>> requestBeans = new InheritableThreadLocal<>();

    public static void autoRegister() {
        try {
            URL res = Context.class.getResource(
                    "/" + Context.class.getName().replace('.', '/') + ".class");
            Path classPath = new File(res.toURI()).toPath().getParent().getParent().getParent().getParent();
            try(Stream<Path> stream = Files.walk(classPath)){
                stream.filter(p -> !Files.isDirectory(p))
                        .filter(p -> p.toString().endsWith(".class"))
                        .map(p -> classPath.relativize(p))
                        .map(p -> p.toString().replace(File.separatorChar, '.'))
                        .map(n -> n.substring(0, n.length() - 6))
                        .map(n -> {
                            try {
                                return Class.forName(n);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .filter(c -> c.isAnnotationPresent(Named.class))
                        .forEach(c -> {
                            System.out.println(c.toString());
                            String simpleName = c.getSimpleName();
                            register(simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1), c);
                        });
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Collection<Map.Entry<String, Class>> registeredClasses() {
        return types.entrySet();
    }

    public static void register(String name, Class type) {
        types.put(name, type);
    }

    public static Object getBean(String name) {
        System.out.println(types);
        Class type = types.get(name);
        Objects.requireNonNull(type, name + " not found.");

        Map<String, Object> scope;
        if (type.isAnnotationPresent(RequestScoped.class)) {
            scope = requestBeans.get();
            if (scope == null) {
                scope = new HashMap<>();
                requestBeans.set(scope);
            }
        } else {
            scope = beans;
        }
        // Java8まではOKだったけどJava9でだめになったみたい
        // 参考: https://stackoverflow.com/questions/54824656/since-java-9-hashmap-computeifabsent-throws-concurrentmodificationexception-on
        /*
        return scope.computeIfAbsent(name, key -> {
            try {
                return createObject(type);
            } catch (InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException ex)
            {
                throw new RuntimeException(name + " can not instanciate", ex);
            }
        });
         */
        if (scope.containsKey(name)) {
            return scope.get(name);
        }
        try {
            Object object = createObject(type);
            scope.put(name, object);
            return object;
        } catch (InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException ex)
        {
                throw new RuntimeException(name + " can not instanciate", ex);
        }
    }

    private static <T> T createObject(Class<T> type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T object;
        if (Stream.of(type.getDeclaredMethods()).anyMatch(m -> m.isAnnotationPresent(InvokeLog.class))) {
            object = wrap(type).getDeclaredConstructor().newInstance();
        } else {
            object = type.getDeclaredConstructor().newInstance();
        }
        inject(type, object);
        return object;
    }

    private static <T> Class<? extends T> wrap(Class<T> type) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass orgCls = pool.get(type.getName());

            CtClass cls = pool.makeClass(type.getName() + "$$");
            cls.setSuperclass(orgCls);

            for(CtMethod method : orgCls.getDeclaredMethods()) {
                if (!method.hasAnnotation(InvokeLog.class)) {
                    continue;
                }
                CtMethod newMethod = new CtMethod(
                        method.getReturnType(), method.getName(), method.getParameterTypes(), cls);
                newMethod.setExceptionTypes(method.getExceptionTypes());
                newMethod.setBody(
                        "{"
                                + "  System.out.println(java.time.LocalDateTime.now() + "
                                + "\":" + method.getName() + " invoked.\"); "
                                + "  return super." + method.getName() + "($$);"
                                + "}");
                cls.addMethod(newMethod);
            }
            return (Class<? extends T>) cls.toClass();
        } catch (NotFoundException | CannotCompileException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> void inject(Class<T> type, T object) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : type.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);
            Object bean;
            if (!type.isAnnotationPresent(RequestScoped.class) && field.getType().isAnnotationPresent(RequestScoped.class)) {
                bean = scopeWrapper(field.getType(), field.getName());
            } else {
                bean = getBean(field.getName());
            }
            field.set(object, bean);
        }
    }
    private static Set<String> cannotOverrides = Stream.of("finalize", "clone").collect(Collectors.toSet());
    private static <T> T scopeWrapper(Class<T> type, String name) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cls = pool.getOrNull(type.getName() + "$$_");
            if (cls == null) {
                CtClass orgCls = pool.get(type.getName());
                cls = pool.makeClass(type.getName() + "$$_");
                cls.setSuperclass(orgCls);

                CtClass tl = pool.get(LocalCache.class.getName());
                CtField org = new CtField(tl, "org", cls);
                cls.addField(org, "new " + LocalCache.class.getName() + "(\"" + name + "\");");

                for (CtMethod method : orgCls.getMethods()) {
                    if (Modifier.isFinal(method.getModifiers()) | cannotOverrides.contains(method.getName())) {
                        continue;
                    }
                    CtMethod override = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), cls);
                    override.setExceptionTypes(method.getExceptionTypes());
                    override.setBody(
                            "{"
                                    + "  return ((" + type.getName() + ")org.get())." + method.getName() + "($$);"
                                    + "}");
                    cls.addMethod(override);
                }
            }
            return (T) cls.toClass().getDeclaredConstructor().newInstance();
        } catch (NotFoundException | IllegalAccessException | CannotCompileException |
                InstantiationException | NoSuchMethodException | InvocationTargetException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
