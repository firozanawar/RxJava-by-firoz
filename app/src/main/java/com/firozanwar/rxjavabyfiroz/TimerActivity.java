package com.firozanwar.rxjavabyfiroz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import rx.Subscription;

//import rx.android.schedulers.AndroidSchedulers;

public class TimerActivity extends AppCompatActivity {
    private Subscription subscription;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timer = findViewById(R.id.timer);
//        startTimerTask();
    }

//    private void startTimerTask() {
//        subscription = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        timer.setText("Timer Called : " + aLong);
//                    }
//                });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }
}
