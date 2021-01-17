package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button food = findViewById(R.id.food);
    Button thing = findViewById(R.id.thing);
    Button place = findViewById(R.id.thing);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void setPlaceMode(View view){
        StartActivity1.mode = "place";
        RunActivity.mode = "place";
    }

    public void setThingMode(View view){
        StartActivity1.mode = "thing";
        RunActivity.mode = "thing";
    }

    public void setFoodMode(View view){
        StartActivity1.mode = "food";
        RunActivity.mode = "food";
    }
}