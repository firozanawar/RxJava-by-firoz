package com.firozanwar.rxjavabyfiroz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitWithRxActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progress;
    private AllPostsAdapter adapter;
    private Subscription subscription;
    private JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_retrofit_with_rx);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");

        //initialize adapter
        adapter = new AllPostsAdapter();

        //reference recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //setup layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter to recycler view
        recyclerView.setAdapter(adapter);

        api = RetrofitProvider.get().create(JsonPlaceHolderApi.class);

        //get all posts
        subscription = api.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> progress.show())
                .doOnCompleted(() -> progress.dismiss())
                .subscribe(posts ->{
                        adapter.setData(posts);
                },err -> {
                    err.printStackTrace();                               //printing stack trace in case of err
                    progress.dismiss();
                });
    }
}
