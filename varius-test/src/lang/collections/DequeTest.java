package lang.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class DequeTest {
    public static void main(String... args) {
        Deque<String> books = new ArrayDeque<>();
        books.offer("Java パフォーマンス");
        books.offerFirst("みんなのJava");
        books.offerLast("Effective Java");
        System.out.println(books.pollFirst()); // みんなのJava
        System.out.println(books.pollLast()); // Effective Java

        Stack<String> bookStack = new Stack<>();
        bookStack.push("aa");
    }
}
