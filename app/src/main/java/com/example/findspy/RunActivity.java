package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RunActivity extends AppCompatActivity {

    public int spy;
    public int playerNumber;
    public int playerCounter;
    public boolean hideText;
    public boolean showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        playerCounter = 1;
        hideText = false;
        showText = true;
        setSpy();
    }

    private void setSpy() {
        Random random = new Random();
        Button button = (Button) findViewById(R.id.playerNumber);
        playerNumber = Integer.parseInt((String) button.getText());
        spy = random.nextInt(playerNumber) + 1;
    }

    public void giveRole(View view) {
        Button button = (Button) findViewById(R.id.role);
        if(showText) {
            if (playerCounter != spy) {
                button.setText(R.string.places);
            } else {
                button.setText(R.string.spy);
            }
            playerCounter++;
            hideText = true;
        }
        else {
            button.setText(R.string.role);
        }
        hideAndShow();
    }

    public void hideAndShow(){
        showText = !showText;
        TextView textView = (TextView) findViewById(R.id.hide);
        if(hideText){
            textView.setText(R.string.hide);
        }
        else{
            textView.setText("");
        }
        hideText = false;
    }
}