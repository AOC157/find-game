package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
        setContentView(R.layout.activity_thing);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_up);
    }
}