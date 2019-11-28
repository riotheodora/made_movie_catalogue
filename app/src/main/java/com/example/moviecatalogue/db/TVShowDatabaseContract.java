package com.example.moviecatalogue.db;

import android.provider.BaseColumns;

public class TVShowDatabaseContract {
    static String TABLE_NAME = "favoriteTVShow";

    public static final class TVShowColumns implements BaseColumns {
        public static String _ID = "show_id";
        public static String TITLE = "show_title";
        public static String OVERVIEW = "show_overview";
        public static String POSTER = "show_poster";
    }
}
