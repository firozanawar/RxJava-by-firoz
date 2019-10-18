package com.firozanwar.rxjavabyfiroz.ravitamada;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BufferOperator {

    public static final String TAG = BufferOperator.class.getSimpleName();

    public void run() {

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Integer>>() {
                    @Override
                    public void onNext(List<Integer> integer) {
                        for (Integer i : integer) {
                            Log.d(TAG, "Item: " + i);
                        }
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All items emitted!");
                    }
                });
    }
}
