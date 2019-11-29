package com.example.moviecatalogue.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieDatabaseContract {
    public static final String AUTHORITY = "com.example.moviecatalogue";
    private static final String SCHEME = "content";

    public static String TABLE_NAME = "favoriteMovie";

    public static final class MovieColumns implements BaseColumns {
        public static String _ID = "movie_id";
        public static String TITLE = "movie_title";
        public static String OVERVIEW = "movie_overview";
        public static String POSTER = "movie_poster";
    }

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();
}