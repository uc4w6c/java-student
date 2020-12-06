package lang.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
    public static void main(String... args) {
        // Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        // HashTableの衝突テスト

        Map<Book, String> hashMap = new HashMap<>();
        hashMap.put(new Book("Taro"), "1");
        hashMap.put(new Book("yamato"), "2");
        hashMap.forEach((book, value)-> {
            System.out.println(book.getName());
            System.out.println(value);
        });

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
