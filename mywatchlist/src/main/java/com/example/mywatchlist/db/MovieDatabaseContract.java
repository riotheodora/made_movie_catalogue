package com.example.mywatchlist.db;

import android.provider.BaseColumns;

public class MovieDatabaseContract {
    static String TABLE_NAME = "favoriteMovie";

    public static final class MovieColumns implements BaseColumns {
        public static String _ID = "movie_id";
        public static String TITLE = "movie_title";
        public static String OVERVIEW = "movie_overview";
        public static String POSTER = "movie_poster";
    }
}
