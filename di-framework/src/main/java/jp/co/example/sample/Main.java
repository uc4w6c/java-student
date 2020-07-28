package jp.co.example.sample;

import jp.co.example.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Context.autoRegister();
        Bar bar = (Bar) Context.getBean("bar");
        bar.showMessage();

        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; ++i) {
            es.execute(() -> {
                bar.longProcess();
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
        }
        es.shutdown();
    }
}
