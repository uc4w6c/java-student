package lang.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
    public static void main(String... args) {
        // Map<String, String> hashMap = new HashMap<>();

        // HashTableの衝突テスト

        Map<Book, String> hashMap = new HashMap<>();
        hashMap.put(new Book("Taro"), "1");
        hashMap.put(new Book("yamato"), "2");
        hashMap.forEach((book, value)-> {
            System.out.println(book.getName());
            System.out.println(value);
        });

        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(25, "Taro");
        treeMap.put(20, "Hanako");
        treeMap.put(30, "Yamao");
        // treeMap.put(31, "Yamao");
        treeMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(25, "Taro");
        linkedHashMap.put(20, "Hanako");
        linkedHashMap.put(30, "Yamao");
        linkedHashMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
    }

    private static class Book {
        private String name;
        public Book(String name) {
            this.name = name;
        }
        @Override
        public int hashCode() {
            return 1;
        }
        public String getName() {
            return name;
        }
    }
}
