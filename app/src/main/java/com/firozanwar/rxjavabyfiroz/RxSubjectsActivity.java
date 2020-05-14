package com.firozanwar.rxjavabyfiroz;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class RxSubjectsActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaFirozActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_firoz);

        Subjects();
    }

    private void Subjects() {

        // Case #1
        //PublishSubject<Integer> source = PublishSubject.create();

        // Case #2
        // BehaviorSubject<Integer> source = BehaviorSubject.create();

        // Case #3
        //ReplaySubject<Integer> source = ReplaySubject.create();

        // Case #4
        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver());

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe(getSecondObserver());

        // It will get 4 and onComplete for second observer also
        source.onNext(4);
        source.onComplete();
    }

    private void BehaviorPublish(){

    }

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe FirstObserver : ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext FirstObserver: "+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError FirstObserver: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete FirstObserver: ");
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe SecondObserver: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext SecondObserver: "+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError SecondObserver: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete SecondObserver: ");
            }
        };
    }

    /**
     * Output:-
     *
     * Case #1
     * onSubscribe FirstObserver :
     * onNext FirstObserver: 1
     * onNext FirstObserver: 2
     * onNext FirstObserver: 3
     * onSubscribe SecondObserver:
     * onNext FirstObserver: 4
     * onNext SecondObserver: 4
     * onComplete FirstObserver:
     * onComplete SecondObserver:
     *
     * Case #2
     * onSubscribe FirstObserver :
     * onNext FirstObserver: 1
     * onNext FirstObserver: 2
     * onNext FirstObserver: 3
     * onSubscribe SecondObserver:
     * onNext SecondObserver: 3
     * onNext FirstObserver: 4
     * onNext SecondObserver: 4
     * onComplete FirstObserver:
     * onComplete SecondObserver:
     *
     * Case #3
     * onSubscribe FirstObserver :
     * onNext FirstObserver: 1
     * onNext FirstObserver: 2
     * onNext FirstObserver: 3
     * onSubscribe SecondObserver:
     * onNext SecondObserver: 1
     * onNext SecondObserver: 2
     * onNext SecondObserver: 3
     * onNext FirstObserver: 4
     * onNext SecondObserver: 4
     * onComplete FirstObserver:
     * onComplete SecondObserver:
     *
     * Case #4
     * onSubscribe FirstObserver :
     * onSubscribe SecondObserver:
     * onNext FirstObserver: 4
     * onComplete FirstObserver:
     * onNext SecondObserver: 4
     * onComplete SecondObserver:
     *
     */
}
