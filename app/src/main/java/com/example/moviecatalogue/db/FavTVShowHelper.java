package com.example.moviecatalogue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.moviecatalogue.db.TVShowDatabaseContract.TABLE_NAME;
import static com.example.moviecatalogue.db.TVShowDatabaseContract.TVShowColumns._ID;

public class FavTVShowHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static FavTVShowHelper INSTANCE;
    private static SQLiteDatabase database;

    private FavTVShowHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavTVShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavTVShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    // open TV Show database connection
    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    // close TV Show database connection
    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    // fetch data
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    // store data
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    // update data
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    //delete data
    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}