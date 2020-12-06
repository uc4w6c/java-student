package lang.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableListTest {
    public static void main(String... args) {
        List<String> books = new ArrayList<>();
        books.add("Java パフォーマンス");
        // books.add("みんなのJava");
        books = Collections.unmodifiableList(books);
        books.add("Effective Java");
    }
}
