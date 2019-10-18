package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.utils.AppConstant;
import com.firozanwar.rxjavabyfiroz.utils.ObserverAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;


public class TakeUntilExampleActivity extends AppCompatActivity {

    private static final String TAG = TakeUntilExampleActivity.class.getSimpleName();
    private Button btn;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);
        btn.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {

        Observable<Long> timerObservable = Observable.timer(5, TimeUnit.SECONDS);
        timerObservable.subscribe(new ObserverAdapter<Long>() {
            @Override
            public void onComplete() {
                String print = " Timer completed";
                textView.append(print);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, print);
            }
        });
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), new BiFunction<String, Long, String>() {
                    @Override
                    public String apply(String s, Long aLong) throws Exception {
                        return s;
                    }
                }).takeUntil(timerObservable).subscribe(getObserver());

    }

    private Observable<String> getObservable() {
        return Observable.just("Alpha", "Beta", "Cupcake", "Doughnut", "Eclair", "Froyo", "GingerBread",
                "Honeycomb", "Ice cream sandwich");
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "" + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
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