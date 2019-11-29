package com.example.watchlist.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieDatabaseContract {
    private static final String AUTHORITY = "com.example.moviecatalogue";
    private static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        static final String TABLE_NAME = "favoriteMovie";
        public static final String _ID = "movie_id";
        public static final String TITLE = "movie_title";
        public static final String OVERVIEW = "movie_overview";
        public static final String POSTER = "movie_poster";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}