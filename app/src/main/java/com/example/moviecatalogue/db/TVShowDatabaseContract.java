package com.example.moviecatalogue.db;

import android.provider.BaseColumns;

public class TVShowDatabaseContract {
    static String TABLE_NAME = "favoriteTVShow";

    static final class TVShowColumns implements BaseColumns {
        static String TITLE = "show_title";
        static String OVERVIEW = "show_overview";
        static String POSTER = "show_poster";
    }
}
