package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StartActivity1 extends AppCompatActivity {

    Spinner timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
        setTimer();
    }

    private void setTimer() {
        String[] minute = new String[]{"1 min" , "2 min" , "3 min" , "4 min" , "5 min" , "6 min"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,minute);
        timer.setAdapter(adapter);
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

    public void displayPlaces(View view){
        Intent intent = new Intent(StartActivity1.this,PlacesActivity.class);
        startActivity(intent);
    }

    public void runGame(View view){
        Intent intent = new Intent(StartActivity1.this,RunActivity.class);
        Button playerButton = (Button) findViewById(R.id.playerNumber);
        Button spyButton = (Button) findViewById(R.id.spyNumber);
        RunActivity.playerNumber = Integer.parseInt((String)playerButton.getText());
        RunActivity.spyNumber = Integer.parseInt((String)spyButton.getText());
        RunActivity.time = getTime();
        startActivity(intent);
    }

    private int getTime() {
        String text = timer.getSelectedItem().toString();
        return Integer.parseInt(String.valueOf(text.charAt(0)));
    }
}