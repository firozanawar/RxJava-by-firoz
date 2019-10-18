package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.JsonPlaceHolderApi;
import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.RetrofitProvider;
import com.firozanwar.rxjavabyfiroz.model.User;
import com.firozanwar.rxjavabyfiroz.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ZipExampleActivity extends AppCompatActivity {

    private static final String TAG = ZipExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;
    private JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);
        api = RetrofitProvider.get().create(JsonPlaceHolderApi.class);
        btn.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {
        Observable.zip(getObservable1(), getObservable2(), new BiFunction<List<User>, List<User>, List<User>>() {
            @Override
            public List<User> apply(List<User> users, List<User> users2) throws Exception {
                return Utils.filterUserWhoLovesBoth(users, users);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getObserver());
    }

    private Observable<List<User>> getObservable1() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesCricket());
                    e.onComplete();
                }
            }
        });
    }

    private Observable<List<User>> getObservable2() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesFootball());
                    e.onComplete();
                }
            }
        });
    }

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> users) {
                for (User user : users) {
                    Log.d(TAG, " firstname : " + user.firstname);
                }
                Log.d(TAG, " onNext : " + users.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete");
            }
        };
    }
}