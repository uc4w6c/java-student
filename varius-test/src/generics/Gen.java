package generics;

public class Gen<T> {
    private T var1;
    public Gen(T var1) {
        this.var1 = var1;
    }

    public T getVar1() {
        return var1;
    }

    public void setVar1(T var1) {
        this.var1 = var1;
    }
}
