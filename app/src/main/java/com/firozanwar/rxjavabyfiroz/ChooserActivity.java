package com.firozanwar.rxjavabyfiroz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.rxjavabyfiroz.ravitamada.Example1Activity;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        Button btn = findViewById(R.id.btn_rxjava_by_firoz);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(ChooserActivity.this, RxSubjectsActivity.class));
        });

        btn = findViewById(R.id.btn_rxjava_by_mindorks);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(ChooserActivity.this, MainActivity.class));
        });

        btn = findViewById(R.id.btn_rxjava_by_ravi);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(ChooserActivity.this, Example1Activity.class));
        });
    }
}
