package com.example.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moviecatalogue.db.FavTVShowHelper;

import static com.example.moviecatalogue.db.MovieDatabaseContract.AUTHORITY;
import static com.example.moviecatalogue.db.MovieDatabaseContract.CONTENT_URI;
import static com.example.moviecatalogue.db.MovieDatabaseContract.TABLE_NAME;

public class FavTVShowProvider extends ContentProvider {
    private static final int TV_SHOW = 1;
    private static final int TV_SHOW_ID = 2;
    private FavTVShowHelper favTVShowHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, TV_SHOW);

        sUriMatcher.addURI(AUTHORITY,
                TABLE_NAME + "/#",
                TV_SHOW_ID);
    }

    @Override
    public boolean onCreate() {

        favTVShowHelper = FavTVShowHelper.getInstance(getContext());
        favTVShowHelper.open();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case TV_SHOW:
                cursor = favTVShowHelper.queryAll();
                break;
            case TV_SHOW_ID:
                cursor = favTVShowHelper.queryById(uri.getLastPathSegment());
                break ;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added;
        switch (sUriMatcher.match(uri)) {
            case TV_SHOW:
                added = favTVShowHelper.insert(values);
                break;
            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return Uri.parse(CONTENT_URI + "/" + added);

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case TV_SHOW_ID:
                updated = favTVShowHelper.update(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return updated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case TV_SHOW_ID:
                deleted = favTVShowHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return deleted;
    }
}
