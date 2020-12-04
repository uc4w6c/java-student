package lang.collections;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> arrayList = new LinkedList<>();
        addLast(arrayList);
        addRandom(arrayList);
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
        for (int i = 2; i < 1_000; i++) {
            // list.add(random.nextInt(i + 1), j++);
            list.add(1, j++);
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(list.getClass() + ": addRandom :" + ChronoUnit.MILLIS.between(startTime,endTime));
        return list;
    }
}
// class java.util.ArrayList: addLast :561
// class java.util.ArrayList: addRandom :14714

// class java.util.LinkedList: addLast :2058
// class java.util.LinkedList: addRandom :0
