package hello;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableHello {
    public static void main(String[] args) throws Exception {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                var datas = List.of("Hello, World!", "こんにちは、世界!");

                for (var data : datas) {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    emitter.onNext(data);
                }
                emitter.onComplete();
            }
        });

        observable.observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 何もしない
                    }

                    @Override
                    public void onNext(String item) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + item);
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 完了しました");
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });

        Thread.sleep(500L);
    }
}
