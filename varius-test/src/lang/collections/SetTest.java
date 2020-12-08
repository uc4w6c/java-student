package lang.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String... args) {
        Set<String> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        String test = "Java";

        hashSet.add("test1");
        hashSet.add("test2");
        if (hashSet.contains("test2")) {
            System.out.println("yes");
        }
        String[] list = new String[16];
        list[0] = "test1";
        list[2] = "test2";
        System.out.println(15 & 33); // 多分 modしている
        System.out.println(list[15 & 0]);

        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(1);
    }
}
