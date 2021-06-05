package hello;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Hello {
    public static void main(String[] args) throws Exception {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                var datas = List.of("HelloWorld!", "こんにちは、世界!");

                for (var data : datas) {
                    if (emitter.isCancelled()) {
                        return;
                    }

                    emitter.onNext(data);
                }

                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    // データ数のリクエストおよび購読の解除を行うオブジェクト
                    private Subscription subscription;

                    // 購読が開始された際の処理
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        this.subscription.request(1L);
                    }

                    // データを受け取った際の処理
                    @Override
                    public void onNext(String data) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);

                        this.subscription.request(1L);
                    }

                    // 完了を通知された際の処理
                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 完了しました");
                    }

                    // エラーを通知された際の処理
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }

                });
        Thread.sleep(500L);
    }
}
