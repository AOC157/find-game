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

    public static final int NUMBER_OF_PLACES = 24;    
    public static final int NUMBER_OF_FOODS = 3;
    public static final int NUMBER_OF_THINGS = 3;

    public static int playerNumber;
    public static int spyNumber;
    public int playerCounter;
    public int spyCounter;
    public boolean roleDisplayer;
    public static int time;
    public String answer;
    public int[] spy;
    public static String mode;
    public static boolean jokerBool;
    public int joker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        setContentView(R.layout.activity_run);
        playerCounter = 0;
        joker = 0;
        roleDisplayer = false;
        spy = new int[spyNumber];
        answer = setAnswer();
        setSpy();
        setJoker();
    }

    private String setAnswer() {
        switch (mode){
            case "food":
                answer = setFood();
                break;
            case "thing":
                answer = setThing();
                break;
            case "place":
                answer = setPlace();
                break;
        }
        return answer;
    }

    private String setThing() {
        Random random = new Random();
        int thingIndex = random.nextInt(NUMBER_OF_THINGS);
        return getResources().getStringArray(R.array.things)[thingIndex];
    }

    private String setFood() {
        Random random = new Random();
        int foodIndex = random.nextInt(NUMBER_OF_FOODS);
        return getResources().getStringArray(R.array.foods)[foodIndex];
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    
    
    private void setJoker() {
        if(!jokerBool){
            return;
        }
        Random random = new Random();
        int jokerIndex = random.nextInt(spyNumber);
        joker = spy[jokerIndex];
    }

    private String setPlace() {
        Random random = new Random();
        int placeIndex = random.nextInt(NUMBER_OF_PLACES);
        return getResources().getStringArray(R.array.places)[placeIndex];
    }

    public void setSpy() {
        if(playerNumber == spyNumber){
            setAllPlayersSpy();
            return;
        }
        Random random = new Random();
        for(int counter = 0; counter < spyNumber; counter++) {
            int tempSpy = random.nextInt(playerNumber) + 1;
            if(newSpy(tempSpy)){
                spy[counter] = tempSpy;
            }
            else{
                counter--;
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
            if(spyCounter < spyNumber){
                if (playerCounter != spy[spyCounter]) {
                    button.setText(answer);
                }
                else {
                    if(spy[spyCounter] != joker){
                        button.setText(R.string.spy);
                    }
                    else{
                        button.setText("Joker : " + answer);
                    }
                    spyCounter++;
                }
            }
            else{
                button.setText(answer);
            }
            textView.setText(R.string.hide);
            roleDisplayer = true;
        }
        else {
            if(playerCounter == playerNumber){
                startTimer();
            }
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
        new CountDownTimer(time*60*1000,500) {
            @Override
            public void onTick(long l) {
                int seconds = (int) l / 1000;
                int minutes = seconds / 60;
                int realSeconds = seconds % 60;
                if(realSeconds >= 10){
                    textView.setText(String.format("%2d",minutes) + ":" + String.format("%2d",realSeconds));
                }
                else{
                    textView.setText(String.format("%2d",minutes) + ":0" + realSeconds);
                }
            }

            @Override
            public void onFinish() {
                textView.setText(R.string.finish);
            }
        }.start();
    }
}