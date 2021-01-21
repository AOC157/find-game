package com.example.findspy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.findSpy);
        replaceImage();
        playIntroMusic();
        startMainActivity();
    }

    private void replaceImage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(image,"y",600f,170f);
        animator.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

    private void playIntroMusic() {
        if(player == null){
            player = MediaPlayer.create(this, R.raw.intromusic);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayer();
            }
        });
    }

    private void startMainActivity() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.slide_out_left);
                stopPlayer();
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,3000);
    }

    private void stopPlayer() {
        if(player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
