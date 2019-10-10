package com.firozanwar.rxjavabyfiroz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.async).setOnClickListener(this::onClick);
        findViewById(R.id.timer).setOnClickListener(this::onClick);
        findViewById(R.id.retrofit).setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.async) {
            Intent intent = new Intent(MainActivity.this, AsyncToRxActivity1.class);
            startActivity(intent);
        } else if (view.getId() == R.id.timer) {
            Intent intent = new Intent(MainActivity.this, TimerActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.retrofit) {
            Intent intent = new Intent(MainActivity.this, RetrofitWithRxActivity1.class);
            startActivity(intent);
        }
    }
}
