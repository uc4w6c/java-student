package lang.collections;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        addRandom(arrayList);
        System.out.println(arrayList.get(2));

        List<Integer> linkedList = new LinkedList<>();
    }
    public static List<Integer> addRandom(List<Integer> list) {
        LocalDateTime startTime = LocalDateTime.now();
        // Random random = new Random();
        int j = 1;
        for (int i = 0; i < 10_000_000; i++) {
            // list.add(random.nextInt());
            list.add(j++);
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(list.getClass() + ":" + ChronoUnit.MILLIS.between(startTime,endTime));
        return list;
    }
}
