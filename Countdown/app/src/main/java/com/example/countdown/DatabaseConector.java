package com.example.countdown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConector extends SQLiteOpenHelper {
    private static final String NAME = "countdownDatabase";
    private static final int VERSION = 5;

    public DatabaseConector(Context c){
        super(c,NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
            db.execSQL("DELETE FROM " + CreateTable.TABLE_NAME);
    }
}
