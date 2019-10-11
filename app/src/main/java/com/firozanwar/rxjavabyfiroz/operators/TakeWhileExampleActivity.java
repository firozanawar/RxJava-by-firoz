package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.model.ApiUser;
import com.firozanwar.rxjavabyfiroz.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class TakeWhileExampleActivity extends AppCompatActivity {

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
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).takeWhile(new Predicate<List<ApiUser>>() {
            @Override
            public boolean test(List<ApiUser> s) throws Exception {
                return s.size() >= 3;
            }
        }).subscribe(getObserver());
    }


    protected Observable<List<ApiUser>> getObservable() {
        return Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> emitter) throws Exception {
                if (emitter != null) {
                    emitter.onNext(Utils.getApiUserList());
                    emitter.onComplete();
                }
            }
        });
    }

    private Observer<List<ApiUser>> getObserver() {
        return new Observer<List<ApiUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "" + d.isDisposed());
            }

            @Override
            public void onNext(List<ApiUser> s) {
                Log.d(TAG, "onNext : " + s.size());
                for (ApiUser u : s) {
                    Log.d(TAG, "onNext : " + u.firstname);
                }
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
