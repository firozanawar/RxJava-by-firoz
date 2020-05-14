package com.firozanwar.rxjavabyfiroz.ravitamada;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DistinctOperator {

    public static final String TAG = DistinctOperator.class.getSimpleName();

    public void run() {

        Observable.just(10, 10, 15, 20, 100, 200, 100, 300, 20, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
