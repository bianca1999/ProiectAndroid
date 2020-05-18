package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ImageView image1, image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1=(ImageView) findViewById(R.id.new_game);
        image2=(ImageView) findViewById(R.id.hg_scores);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Leaderboard.class);
                startActivity(intent);
            }
        });
    }
}

