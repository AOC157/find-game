package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
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
}