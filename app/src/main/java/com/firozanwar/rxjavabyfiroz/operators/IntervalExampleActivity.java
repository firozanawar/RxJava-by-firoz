package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class IntervalExampleActivity extends AppCompatActivity {

    private static final String TAG = IntervalExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private AtomicLong lastTick = new AtomicLong(0L);
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    void resume() {
        Log.d(TAG,"resumed");
        disposable = Observable.interval(5, TimeUnit.SECONDS, Schedulers.io())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long tick) throws Exception {
                        return lastTick.getAndIncrement();
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long tick) throws Exception {
                        Log.d(TAG, "tick = " + tick);
                    }
                });
    }

    void stop() {
        if (disposable != null && !disposable.isDisposed()) {
            Log.d(TAG,"stopped");
            disposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clearing it : do not emit after destroy
    }

    /*
     * simple example using interval to run task at an interval of 2 sec
     * which start immediately
     */
    private void doSomeWork() {
        getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getObserver());
    }

    private Observable<Long> getObservable() {
        return Observable.interval(1, 2, TimeUnit.SECONDS);
    }

    private Observer<Long> getObserver() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long s) {
                Log.d(TAG, "onNext : " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }
}