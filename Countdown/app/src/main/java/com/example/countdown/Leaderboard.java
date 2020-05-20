package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {
    TextView scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        scores=findViewById(R.id.scores);
        SQLiteDatabase db= new DatabaseConector(this).getReadableDatabase();
        String[] item={CreateTable._ID, CreateTable.NAME, CreateTable.SCORE};
        Cursor cursor= db.query(CreateTable.TABLE_NAME,item,null,null,null,null,CreateTable.SCORE);
        StringBuffer buffer =new StringBuffer();
        int i=1;
        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex(CreateTable.NAME));
            String score= cursor.getString(cursor.getColumnIndex(CreateTable.SCORE));
            buffer.append( i+") "+name+" - "+score+" sec \n");
            i++;
        }
        scores.setText(buffer.toString());
    }
}
