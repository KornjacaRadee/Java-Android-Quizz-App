package com.example.projekat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "game_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "players";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_SCORE + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ovdje možete implementirati logiku za ažuriranje baze podataka
        // Ako ne želite ažuriranje baze podataka, možete izostaviti ovu metodu
    }
}
