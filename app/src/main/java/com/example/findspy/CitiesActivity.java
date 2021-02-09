package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_up);
    }
}