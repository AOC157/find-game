package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class RunActivity extends AppCompatActivity {

    public static int playerNumber;
    public static int spyNumber;
    public int playerCounter;
    public int spyCounter;
    public boolean roleDisplayer;
    public int[] spy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        playerCounter = 0;
        roleDisplayer = false;
        spy = new int[spyNumber];
        setSpy();
    }

    public void setSpy() {
        if(playerNumber == spyNumber){
            setAllPlayersSpy();
        }
        Random random = new Random();
        for(int counter = 0; counter < spyNumber; counter++) {
            int tempSpy = random.nextInt(playerNumber) + 1;
            if(newSpy(tempSpy)){
                spy[counter] = tempSpy;
            }
        }
        Arrays.sort(spy);
    }

    private void setAllPlayersSpy() {
        for(int index = 0; index < spyNumber; index++){
            spy[index] = index + 1;
        }
    }

    private boolean newSpy(int tempSpy) {
        for (int i : spy) {
            if (i == tempSpy) {
                return false;
            }
        }
        return  true;
    }

    public void giveRole(View view) {
        TextView textView = (TextView) findViewById(R.id.hide);
        Button button = (Button) view;
        if(!roleDisplayer) {
            playerCounter++;
            if (playerCounter != spy[spyCounter]) {
                button.setText(R.string.places);
            }
            else {
                button.setText(R.string.spy);
                spyCounter++;
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