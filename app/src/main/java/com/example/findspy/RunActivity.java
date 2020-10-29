package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RunActivity extends AppCompatActivity {

    public int spy;
    public static int playerNumber;
    public int playerCounter;
    public boolean roleDisplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        playerCounter = 0;
        roleDisplayer = false;
        spy = 1;
        setSpy();
    }

    public void setSpy() {
        Random random = new Random();
        spy = random.nextInt(playerNumber) + 1;
    }

    public void giveRole(View view) {
        TextView textView = (TextView) findViewById(R.id.hide);
        Button button = (Button) view;
        if(!roleDisplayer) {
            playerCounter++;
            if (playerCounter != spy) {
                button.setText(R.string.places);
            } else {
                button.setText(R.string.spy);
            }
            textView.setText(R.string.hide);
            roleDisplayer = true;
            if(playerCounter > playerNumber){
                startTimer();
            }
        }
        else {
            button.setText(R.string.role);
            textView.setText(R.string.space);
            roleDisplayer = false;
        }
    }

    private void startTimer() {
        Button button = (Button) findViewById(R.id.role);
        button.setVisibility(View.GONE);
        TextView hideText = (TextView) findViewById(R.id.hide);
        hideText.setVisibility(View.GONE);
        final TextView textView = findViewById(R.id.timer);
        new CountDownTimer(180000,500) {
            @Override
            public void onTick(long l) {
                int seconds = (int) l / 1000;
                int minutes = seconds / 60;
                int realSeconds = seconds % 60;
                textView.setText(String.format("%2d",minutes) + ":" + String.format("%2d",realSeconds));
            }

            @Override
            public void onFinish() {
                textView.setText(R.string.finish);
            }
        }.start();
    }
}