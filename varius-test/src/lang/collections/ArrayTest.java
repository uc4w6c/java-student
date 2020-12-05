package lang.collections;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        String[] books = new String[1]; // 初期化時に配列の大きさ「0」を指定します。
        books[0] = "Java パフォーマンス";
        System.out.println(books[0]);

        /*
        books = new String[2];
        books[0] = "Java パフォーマンス";
        books[1] = "みんなのJava";
        books[2] = "Effective Java";
         */
        // books[1] = "みんなのJava";
        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        // System.out.println(books[1]);

        books = Arrays.copyOf(books, 2);
        books[1] = "みんなのJava";
        System.out.println(books[0]);
        System.out.println(books[1]);
    }
}
