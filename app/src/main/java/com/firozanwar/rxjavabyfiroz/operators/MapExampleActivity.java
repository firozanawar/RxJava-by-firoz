package com.firozanwar.rxjavabyfiroz.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.model.ApiUser;
import com.firozanwar.rxjavabyfiroz.model.User;
import com.firozanwar.rxjavabyfiroz.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class MapExampleActivity extends AppCompatActivity {

    private static final String TAG = MapExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);
        btn.setOnClickListener(v -> doSomeWork());
    }

    private void doSomeWork() {
        getObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<List<ApiUser>, List<User>>() {
            @Override
            public List<User> apply(List<ApiUser> apiUsers) throws Exception {
                return Utils.convertApiUserListToUserList(apiUsers);
            }
        }).subscribe(getObserver());
    }

    private Observable<List<ApiUser>> getObservable() {
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

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "" + d.isDisposed());
            }

            @Override
            public void onNext(List<User> users) {
                for (User user : users) {
                    Log.d(TAG, "onNext : " + user.firstname);
                }
                Log.d(TAG, "onNext : " + users.size());
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