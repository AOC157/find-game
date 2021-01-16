package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    static String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        setContentView(R.layout.activity_start1);
        jokerBool = false;
        setTimer();
        setJoker();
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.exitMassage);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StartActivity1.this.finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();
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

    public void displayPlaces(View view){
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