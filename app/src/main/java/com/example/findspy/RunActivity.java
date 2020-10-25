package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.Random;

public class RunActivity extends AppCompatActivity {

    public int spyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        setSpy();
    }

    private void setSpy() {
        Random random = new Random();
        Button button = (Button) findViewById(R.id.playerNumber);
    }


}