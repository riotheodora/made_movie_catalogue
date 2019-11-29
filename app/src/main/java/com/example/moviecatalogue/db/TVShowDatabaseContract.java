package com.example.moviecatalogue.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class TVShowDatabaseContract {
    public static final String AUTHORITY = "com.example.moviecatalogue";
    private static final String SCHEME = "content";

    public static String TABLE_NAME = "favoriteTVShow";

    public static final class TVShowColumns implements BaseColumns {
        public static String _ID = "show_id";
        public static String TITLE = "show_title";
        public static String OVERVIEW = "show_overview";
        public static String POSTER = "show_poster";
    }

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();
}
