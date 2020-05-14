package com.firozanwar.rxjavabyfiroz;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import rx.android.schedulers.AndroidSchedulers;

public class AsyncToRxActivity1 extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_async_to_rx);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

//        startAsyncTask("Firoz Anwar");
    }

//    private void startAsyncTask(String input) {
//        Observable.just(input)
//                .map(this::doInBackground)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(this::onPreExecute)
//                .subscribe(this::onPostExecute);
//    }

    private void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    //do background things here
    private int doInBackground(String data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.length();
    }

    //post execution work here
    private void onPostExecute(int result) {
        progressBar.setVisibility(View.GONE);
        textView.setText("Length of input is " + result);
    }
}
