package lang.collections;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {
    public static void main(String... args) {
        /*
        Queue<Integer> numbers = new PriorityQueue<>();
        numbers.offer(5);
        numbers.offer(4);
        numbers.offer(6);
        numbers.offer(9);
        numbers.offer(5);
        numbers.offer(1);
        System.out.println(numbers.poll());
        System.out.println(numbers.poll());
         */
        Queue<Integer> numbers = new PriorityQueue<>();
        numbers.offer(5);
        numbers.offer(4);
        numbers.offer(6);
        numbers.offer(4);
        numbers.offer(7);
        System.out.println(numbers.poll());
        System.out.println(numbers.poll());

        /*
        Queue<String> books = new ArrayBlockingQueue<>(2);
        books.offer("Javaパフォーマンス");
        books.offer("みんなのJava");
        System.out.println(books.poll());
        System.out.println(books.poll());
         */
    }
}
