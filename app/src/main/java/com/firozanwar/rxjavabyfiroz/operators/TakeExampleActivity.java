package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class TakeExampleActivity extends AppCompatActivity {

    private static final String TAG = TakeExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

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

    /* Using take operator, it only emits
     * required number of values. here only 3 out of 5
     */
    private void doSomeWork() {
        getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).take(2).subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("India", "Australia", "Pak", "Sri", "Ban");
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