package com.example.watchlist;

import android.database.Cursor;

import java.util.ArrayList;

import static com.example.watchlist.db.MovieDatabaseContract.MovieColumns.OVERVIEW;
import static com.example.watchlist.db.MovieDatabaseContract.MovieColumns.POSTER;
import static com.example.watchlist.db.MovieDatabaseContract.MovieColumns.TITLE;
import static com.example.watchlist.db.MovieDatabaseContract.MovieColumns._ID;

class MappingHelper {
    static ArrayList<Movie> mapCursorToArrayList(Cursor movieCursor){
        ArrayList<Movie> moviesList = new ArrayList<>();

        while(movieCursor.moveToNext()){
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(OVERVIEW));
            String poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(POSTER));
            moviesList.add(new Movie(id, title, overview, poster_path));
        }
        return moviesList;
    }
}
