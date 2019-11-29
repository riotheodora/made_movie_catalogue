package com.example.mywatchlist.helper;

import android.database.Cursor;

import com.example.mywatchlist.db.MovieDatabaseContract;
import com.example.mywatchlist.db.TVShowDatabaseContract;
import com.example.mywatchlist.entity.Movie;
import com.example.mywatchlist.entity.TVShow;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor movieCursor){
        ArrayList<Movie> moviesList = new ArrayList<>();

        while(movieCursor.moveToNext()){
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns._ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.OVERVIEW));
            String poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.POSTER));
            moviesList.add(new Movie(id, title, overview, poster_path));
        }
        return moviesList;
    }

    public static ArrayList<TVShow> mapFavShowCursorToArrayList(Cursor showCursor){
        ArrayList<TVShow> showsList = new ArrayList<>();

        while(showCursor.moveToNext()){
            int id = showCursor.getInt(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns._ID));
            String title = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.TITLE));
            String overview = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.OVERVIEW));
            String poster_path = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.POSTER));
            showsList.add(new TVShow(id, title, overview, poster_path));
        }
        return showsList;
    }
}
