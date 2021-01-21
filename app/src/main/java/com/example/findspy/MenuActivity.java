package com.example.findspy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        setContentView(R.layout.activity_menu);
    }

    public void setPlaceMode(View view){
        StartActivity1.mode = "place";
        RunActivity.mode = "place";
        Intent intent = new Intent(MenuActivity.this,StartActivity1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.slide_out_left);
    }

    public void setThingMode(View view){
        StartActivity1.mode = "thing";
        RunActivity.mode = "thing";
        Intent intent = new Intent(MenuActivity.this,StartActivity1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.slide_out_left);
    }

    public void setFoodMode(View view){
        StartActivity1.mode = "food";
        RunActivity.mode = "food";
        Intent intent = new Intent(MenuActivity.this,StartActivity1.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.slide_out_left);
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
                MenuActivity.this.finish();
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
}