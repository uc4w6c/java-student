package annotation.runtime;

@MethodInformation(testString = "this is test", testInt = 12345)
public class Main {
    public static void main(String[] args) {
        Main mainInstance = new Main();
        Class clazz = mainInstance.getClass();

        MethodInformation methodInformation = (MethodInformation)clazz.getAnnotation(MethodInformation.class);

        // アノテーション型内ではメソッドで定義されている
        System.out.println(methodInformation.testString());
        System.out.println(methodInformation.testInt());
        System.out.println("Hello World!");
    }
}
