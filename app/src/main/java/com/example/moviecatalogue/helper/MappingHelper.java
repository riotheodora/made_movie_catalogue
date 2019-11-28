package com.example.moviecatalogue.helper;

import android.database.Cursor;

import com.example.moviecatalogue.db.MovieDatabaseContract;
import com.example.moviecatalogue.db.TVShowDatabaseContract;
import com.example.moviecatalogue.entity.FavMovie;
import com.example.moviecatalogue.entity.FavTVShow;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<FavMovie> mapCursorToArrayList(Cursor movieCursor){
        ArrayList<FavMovie> moviesList = new ArrayList<>();

        while(movieCursor.moveToNext()){
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns._ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.OVERVIEW));
            String poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(MovieDatabaseContract.MovieColumns.POSTER));
            moviesList.add(new FavMovie(id, title, overview, poster_path));
        }
        return moviesList;
    }

    public static ArrayList<FavTVShow> mapFavShowCursorToArrayList(Cursor showCursor){
        ArrayList<FavTVShow> showsList = new ArrayList<>();

        while(showCursor.moveToNext()){
            int id = showCursor.getInt(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns._ID));
            String title = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.TITLE));
            String overview = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.OVERVIEW));
            String poster_path = showCursor.getString(showCursor.getColumnIndexOrThrow(TVShowDatabaseContract.TVShowColumns.POSTER));
            showsList.add(new FavTVShow(id, title, overview, poster_path));
        }
        return showsList;
    }
}
