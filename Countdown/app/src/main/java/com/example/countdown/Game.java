package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Game extends AppCompatActivity implements View.OnClickListener{

    private Button startGame;
    private TextView timeTextView;
    private Timer timer;
    private MediaPlayer sound;

    private ProgressBar progressBar;
    private long startTime = 5*1000;
    private final long intervale=1;
    private boolean timeHasStarted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        startGame=findViewById(R.id.start);
        startGame.setOnClickListener(this);

        timeTextView=findViewById(R.id.seconds);
        timer=new Timer(startTime,intervale);
        ((Timer)timer).update();

        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        sound=MediaPlayer.create(Game.this,R.raw.sound);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.start)
            countDownStarter();
    }

    private void countDownStarter(){
        if(!timeHasStarted){
            startGame.setText("STOP");
            progressBar.setVisibility(View.VISIBLE);
            timer.start();
            sound.start();
            timeHasStarted = true;

        }
        else{
            timeHasStarted = false;
            progressBar.setVisibility(View.GONE);
            timer.cancel();
            sound.release();
            gameOver(String.valueOf(timeTextView.getText()));
        }
    }


    public class Timer extends CountDownTimer {
        public Timer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            startTime = millisUntilFinished;
            update();
        }

        @Override
        public void onFinish() {
            timeTextView.setText("Time's up!");
            gameOver(String.valueOf(timeTextView.getText()));
        }

        private void update() {
            int sec = (int) startTime % 60000 / 1000;
            String timeLeftText = "0" + sec + " : " + startTime % 1000 / 10;
            if (sec < 2)
                timeTextView.setVisibility(View.GONE);
            timeTextView.setText(timeLeftText);
        }
    }

    void gameOver(String time){
        sound.release();
        Intent intent = new Intent(getApplicationContext(), Result.class);
        intent.putExtra("SCORE",time);
        startActivity(intent);
    }


}

