package com.example.countdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity implements View.OnClickListener{
    private TextView scoreView;
    String time;
    private ImageView whapp, gmail;
    private Button tryAgain, menu, save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreView = findViewById(R.id.scoreText);
        tryAgain = findViewById(R.id.tryAgain);
        menu = findViewById(R.id.menu);
        save = findViewById(R.id.save);
        gmail=findViewById(R.id.gmail);
        whapp=findViewById(R.id.whapp);

        tryAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
        save.setOnClickListener(this);
        gmail.setOnClickListener(this);
        whapp.setOnClickListener(this);

        Intent i = getIntent();
        time = i.getStringExtra("SCORE");
        scoreView.setText(time);
    }

    public void saveToDatabase (String name, String score){
        SQLiteDatabase database= new DatabaseConector(this).getWritableDatabase();
        ContentValues value= new ContentValues();
        value.put(CreateTable.NAME, name);
        value.put(CreateTable.SCORE,score);
        database.insert(CreateTable.TABLE_NAME,null,value);
    }

    public void userScore(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout, null);
        final EditText editText = view.findViewById(R.id.name);
        dialog.setView(view).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text=editText.getText().toString();
                if(text.isEmpty())
                    Toast.makeText(Result.this, "Type a name!", Toast.LENGTH_LONG).show();
                else
                    saveToDatabase(text,time);
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
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
                userScore();
                break;
            case R.id.gmail:
                String message="This is my score at Countdoun - " + String.valueOf(time)+"! Can you beat it?";
                Intent email= new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_TEXT,message);
                email.putExtra(Intent.EXTRA_SUBJECT,"");
                email.setType("message/rfc822");
                startActivity(email.createChooser(email,"Choose an email adress!"));
                break;
            case R.id.whapp:
                String text="This is my score at Countdoun - " + String.valueOf(time)+"! Can you beat it?";
                Intent whapp = new Intent(Intent.ACTION_SEND);
                whapp.setType("text/plain");
                whapp.setPackage("com.whatsapp");
                whapp.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(whapp, "Share with"));
                break;
            default:
                break;
        }
    }


}
