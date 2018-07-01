package com.codedotman.journalapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 30/06/2018.
 */

public class JournalDbHelper extends SQLiteOpenHelper {

    // The name of the database
    private static final String DATABASE_NAME = "journal.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;


    // Constructor
    JournalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tasks table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + JournalContract.JournalEntry.TABLE_NAME + " (" +
                JournalContract.JournalEntry._ID                + " INTEGER PRIMARY KEY, " +
                JournalContract.JournalEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                JournalContract.JournalEntry.COLUMN_DETAILS    + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JournalContract.JournalEntry.TABLE_NAME);
        onCreate(db);

    }
}
