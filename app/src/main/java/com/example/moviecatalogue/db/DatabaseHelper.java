package com.example.moviecatalogue.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moviecatalogue.db.MovieDatabaseContract.MovieColumns;
import com.example.moviecatalogue.db.TVShowDatabaseContract.TVShowColumns;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbmoviecatalogueapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            MovieDatabaseContract.TABLE_NAME,
            MovieColumns._ID,
            MovieColumns.TITLE,
            MovieColumns.OVERVIEW,
            MovieColumns.POSTER
    );

    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TVShowDatabaseContract.TABLE_NAME,
            TVShowColumns._ID,
            TVShowColumns.TITLE,
            TVShowColumns.OVERVIEW,
            TVShowColumns.POSTER
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override // creating required tables
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + MovieDatabaseContract.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TVShowDatabaseContract.TABLE_NAME);

        // create new tables
        onCreate(db);
    }
}
