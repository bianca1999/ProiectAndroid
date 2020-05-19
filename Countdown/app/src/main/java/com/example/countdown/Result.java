package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener{
    private TextView scoreView;
    String time;
    private Button tryAgain, menu, save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreView = findViewById(R.id.scoreText);
        tryAgain = findViewById(R.id.tryAgain);
        menu = findViewById(R.id.menu);
        save = findViewById(R.id.save);

        tryAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
        save.setOnClickListener(this);

        Intent i = getIntent();
        time = i.getStringExtra("SCORE");
        scoreView.setText(time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tryAgain:
                Intent intent = new Intent(getApplicationContext(), Game.class);
                startActivity(intent);
                break;
            case R.id.menu:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.save:
                break;
            default:
                break;
        }


    }
}
