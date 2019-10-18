package com.firozanwar.rxjavabyfiroz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.firozanwar.rxjavabyfiroz.ravitamada.OperatorsActivity;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RxOperators(View view) {
        startActivity(new Intent(MainActivity.this, com.firozanwar.rxjavabyfiroz.OperatorsActivity.class));
    }

    public void startOperatorsActivity(View view) {
        startActivity(new Intent(MainActivity.this, OperatorsActivity.class));
    }

    public void startNetworkingActivity(View view) {
//        startActivity(new Intent(MainActivity.this, NetworkingActivity.class));
    }

    public void startCacheActivity(View view) {
//        startActivity(new Intent(MainActivity.this, CacheExampleActivity.class));
    }

    public void startRxBusActivity(View view) {
//        ((MyApplication) getApplication()).sendAutoEvent();
//        startActivity(new Intent(MainActivity.this, RxBusActivity.class));
    }

    public void startPaginationActivity(View view) {
//        startActivity(new Intent(MainActivity.this, PaginationActivity.class));
    }

    public void startComposeOperator(View view) {
//        startActivity(new Intent(MainActivity.this, ComposeOperatorExampleActivity.class));
    }

    public void startSearchActivity(View view) {
//        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

    public void RxBasicOperators(View view) {
        startActivity(new Intent(MainActivity.this, OperatorsActivity.class));
    }
}


