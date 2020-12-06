package lang.collections;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // List<Integer> list = new LinkedList<>();
        addLast(list);
        list.clear();
        addRandom(list);
    }

    public static List<Integer> addLast(List<Integer> list) {
        LocalDateTime startTime = LocalDateTime.now();
        int j = 1;
        for (int i = 0; i < 10_000_000; i++) {
            list.add(j++);
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(list.getClass() + ": addLast :" + ChronoUnit.MILLIS.between(startTime,endTime));
        return list;
    }

    public static List<Integer> addRandom(List<Integer> list) {
        LocalDateTime startTime = LocalDateTime.now();
        // Random random = new Random();
        int j = 1;
        list.add(j++);
        list.add(j++);
        for (int i = 2; i < 100_000; i++) {
            // list.add(random.nextInt(i + 1), j++);
            // list.add(1, j++);
            list.add(i / 2, j++);
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(list.getClass() + ": addRandom :" + ChronoUnit.MILLIS.between(startTime,endTime));
        return list;
    }
}
// 2番目の要素に追加の場合
// class java.util.ArrayList: addLast :624
// class java.util.ArrayList: addRandom :937
// class java.util.LinkedList: addLast :2080
// class java.util.LinkedList: addRandom :10

// 中央の要素に追加の場合
// class java.util.ArrayList: addLast :562
// class java.util.ArrayList: addRandom :421
// class java.util.LinkedList: addLast :2084
// class java.util.LinkedList: addRandom :7596
