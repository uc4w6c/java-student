package generics;

public class Sample {
    public static void main(String[] args) {
        Gen<String> g1 = new Gen<>("ABC");
        System.out.print(g1.getVar1());
        g1.setVar1("DEF");
        System.out.println(" " + g1.getVar1());

        Gen<Integer> g2 = new Gen<>(1);
        System.out.print(g2.getVar1());
        g2.setVar1(2);
        System.out.println(" " + g2.getVar1());
    }
}
