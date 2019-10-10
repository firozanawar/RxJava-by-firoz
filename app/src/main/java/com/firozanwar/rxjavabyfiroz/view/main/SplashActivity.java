package com.firozanwar.rxjavabyfiroz.view.main;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.splash_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
        }
    }
}
