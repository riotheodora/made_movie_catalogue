package com.example.moviecatalogue.db;

import android.provider.BaseColumns;

public class MovieDatabaseContract {
    static String TABLE_NAME = "favoriteMovie";

    static final class MovieColumns implements BaseColumns {
        static String TITLE = "movie_title";
        static String OVERVIEW = "movie_overview";
        static String POSTER = "movie_poster";
    }
}
