package com.example.moviecatalogue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.db.MovieDatabaseContract.TABLE_NAME;

public class FavMovieHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static FavMovieHelper INSTANCE;
    private static SQLiteDatabase database;

    private FavMovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    // initiate database
    public static FavMovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavMovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    // open Movie database connection
    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    // close Movie database connection
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