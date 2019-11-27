package com.example.moviecatalogue.helper;

import android.database.Cursor;

import com.example.moviecatalogue.db.MovieDatabaseContract;
import com.example.moviecatalogue.entity.FavMovie;

import java.util.ArrayList;

public class MovieMappingHelper {
    public static ArrayList<FavMovie> mapCursorToArrayList(Cursor movieCursor){
        ArrayList<FavMovie> moviesList = new ArrayList<>();

        while(movieCursor.moveToNext()){
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.OVERVIEW));
            String poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.POSTER));
            moviesList.add(new FavMovie(title, overview, poster_path));
        }
        return moviesList;
    }
}
