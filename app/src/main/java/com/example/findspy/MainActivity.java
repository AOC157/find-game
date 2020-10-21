package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increasePlayer(View view){
        Button button = (Button) findViewById(R.id.playerNumber);
        int playerNumber = Integer.parseInt((String) button.getText()) + 1;
        button.setText(String.valueOf(playerNumber));
    }

    public void decreasePlayer(View view){
        Button button = (Button) findViewById(R.id.playerNumber);
        int playerNumber = Integer.parseInt((String) button.getText()) - 1;
        button.setText(String.valueOf(playerNumber));
    }

    public void increaseSpy(View view){
        Button button = (Button) findViewById(R.id.spyNumber);
        int spyNumber = Integer.parseInt((String) button.getText()) + 1;
        button.setText(String.valueOf(spyNumber));
    }

    public void decreaseSpy(View view){
        Button button = (Button) findViewById(R.id.spyNumber);
        int spyNumber = Integer.parseInt((String) button.getText()) - 1;
        button.setText(String.valueOf(spyNumber));
    }

}