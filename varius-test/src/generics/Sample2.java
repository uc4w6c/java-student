package generics;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
    // 引数で受け取るリストの要素はXクラスまたはそのサブクラス
    public static void method1(List<? extends X> list) {
        System.out.print(list.get(0) + " ");
    }

    // 引数で受け取るリストの要素はYクラスまたはそのスーパークラス
    public static void method2(List<? super Y> list) {
        list.add(new Y());
        System.out.print(list.get(0) + " ");
    }

    public static void main(String[] args) {
        List<X> l1 = new ArrayList<>();
        l1.add(new X());
        List<Y> l2 = new ArrayList<>();
        l2.add(new Y());

        method1(l1);
        method1(l2);

        method2(l1);
        method2(l2);
    }
}

class X {
    public String toString() {
        return "X";
    }
}
class Y extends X {
    public String toString() {
        return "Y";
    }
}
