package com.perdev.compat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Project    AndroidCompat
 * Path       com.perdev.compat
 * Date       2020/09/23 - 17:36
 * Author     Payne.
 * About      类描述：
 */
public class RXUtil {


    public interface RXCallback {
        void call();
    }

    /**
     * 延时任务
     */
    public static Disposable rxDelay(long delay, boolean mainThread, final RXCallback callback) {
        return Observable
                .timer(delay, TimeUnit.MILLISECONDS)
                .observeOn(mainThread ? AndroidSchedulers.mainThread() : Schedulers.computation())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callback.call();
                    }
                })
                .subscribe();
    }


    /**
     * 循环任务
     * 使用返回对象的dispose()方法可以取消任务
     */
    public static Disposable rxInterval(long period, boolean mainThread, final RXCallback callback) {
        return Observable
                .interval(period, TimeUnit.MILLISECONDS)
                .observeOn(mainThread ? AndroidSchedulers.mainThread() : Schedulers.computation())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callback.call();
                    }
                })
                .subscribe();
    }


    private static Disposable rxMapTest() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        return Observable.fromIterable(list)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        //使用map操作符，实现类型转换，并指定线程
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //主线程处理
                    }
                })
                .subscribe();
    }

}
