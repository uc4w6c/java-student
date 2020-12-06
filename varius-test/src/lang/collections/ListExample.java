package lang.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // List<String> books = new ArrayList<>();
        List<String> books = new LinkedList<>();
        books.add("Java パフォーマンス");
        books.add("みんなのJava");
        System.out.println(books.get(1)); // みんなのJava
    }
}
