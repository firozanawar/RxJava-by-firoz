package com.firozanwar.rxjavabyfiroz.ravitamada;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FromOperator {

    public static final String TAG = FromOperator.class.getSimpleName();

    public void run() {

        Observable.fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Task>() {
                    @Override
                    public void onNext(Task task) {
                        Log.d(TAG, "onNext: " + task.getDescription());
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
