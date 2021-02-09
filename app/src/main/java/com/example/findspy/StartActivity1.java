package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import androidx.appcompat.widget.SwitchCompat;

public class StartActivity1 extends AppCompatActivity {

    Spinner timer;
    SwitchCompat joker;
    boolean jokerBool;
    public static String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        setContentView(R.layout.activity_start1);
        serAnswers();
        jokerBool = false;
        setTimer();
        setJoker();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void serAnswers() {
        Button answers = (Button) findViewById(R.id.answers);
        switch (mode){
            case "food":
                answers.setText(R.string.foods);
                return;
            case "thing":
                answers.setText(R.string.things);
                return;
            case "place":
                answers.setText(R.string.places);
                return;
            case "city":
                answers.setText(R.string.cities);
        }
    }

    private void setJoker() {
        joker = (SwitchCompat) findViewById(R.id.joker);
        joker.setChecked(false);
        joker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                jokerBool = !jokerBool;
            }
        });
    }

    private void setTimer() {
        timer = (Spinner) findViewById(R.id.min);
        String[] minute = new String[]{"1 min" , "2 min" , "3 min" , "4 min" , "5 min" , "6 min"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,minute);
        timer.setAdapter(adapter);
        timer.setSelection(2);
    }

    public void increasePlayer(View view){
        Button button = (Button) findViewById(R.id.playerNumber);
        int playerNumber = Integer.parseInt((String) button.getText()) + 1;
        button.setText(String.valueOf(playerNumber));
    }

    public void decreasePlayer(View view){
        Button button = (Button) findViewById(R.id.playerNumber);
        int playerNumber = Integer.parseInt((String) button.getText()) - 1;
        if(playerNumber < 1){
            return;
        }
        Button spyButton = (Button) findViewById(R.id.spyNumber);
        int spyNumber = Integer.parseInt((String) spyButton.getText());
        if(playerNumber < spyNumber){
            spyButton.setText(String.valueOf(playerNumber));
        }
        button.setText(String.valueOf(playerNumber));
    }

    public void increaseSpy(View view){
        Button button = (Button) findViewById(R.id.spyNumber);
        Button playerButton = (Button) findViewById(R.id.playerNumber);
        int spyNumber = Integer.parseInt((String) button.getText()) + 1;
        if(spyNumber > Integer.parseInt((String) playerButton.getText())){
            return;
        }
        button.setText(String.valueOf(spyNumber));
    }

    public void decreaseSpy(View view){
        Button button = (Button) findViewById(R.id.spyNumber);
        int spyNumber = Integer.parseInt((String) button.getText()) - 1;
        if(spyNumber < 1){
            return;
        }
        button.setText(String.valueOf(spyNumber));
    }

    public void displayAnswers(View view){
        switch (mode){
            case "food":
                displayFoods();
                return;
            case "thing":
                displayThings();
                return;
            case "place":
                displayPlaces();
                return;
            case "city":
                displayCities();
        }
    }

    private void displayCities() {
        Intent intent = new Intent(StartActivity1.this,CitiesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
    }

    private void displayThings() {
        Intent intent = new Intent(StartActivity1.this,ThingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
    }

    private void displayFoods() {
        Intent intent = new Intent(StartActivity1.this,FoodsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
    }

    private void displayPlaces(){
        Intent intent = new Intent(StartActivity1.this,PlacesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
    }

    public void runGame(View view){
        Intent intent = new Intent(StartActivity1.this,RunActivity.class);
        Button playerButton = (Button) findViewById(R.id.playerNumber);
        Button spyButton = (Button) findViewById(R.id.spyNumber);
        RunActivity.playerNumber = Integer.parseInt((String)playerButton.getText());
        RunActivity.spyNumber = Integer.parseInt((String)spyButton.getText());
        RunActivity.jokerBool = jokerBool;
        RunActivity.time = getTime();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private int getTime() {
        String text = timer.getSelectedItem().toString();
        return Integer.parseInt(String.valueOf(text.charAt(0)));
    }
}