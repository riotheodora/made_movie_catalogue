package com.example.moviecatalogue.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.ListMovieAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.moviecatalogue.db.MovieDatabaseContract.AUTHORITY;
import static com.example.moviecatalogue.db.MovieDatabaseContract.TABLE_NAME;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    private final List<String> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private Cursor cursor;

    private ListMovieAdapter adapter;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        adapter = new ListMovieAdapter();
    }

    @Override
    public void onDataSetChanged() {
        final long id = Binder.clearCallingIdentity();
        cursor = mContext.getContentResolver().query(CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(id);

        int i = 0;
        while (cursor.moveToNext()) {
            mWidgetItems.add(cursor.getString(cursor.getColumnIndexOrThrow("movie_poster")));
            i++;

        }
    }

    @Override
    public void onDestroy() {
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        getItem(rv, position);

        Bundle extras = new Bundle();
        extras.putInt(MyWatchlistWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private void getItem(RemoteViews rv, int i) {
        try {
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w342/" + mWidgetItems.get(i))
//                        .error(new Bitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_aquaman)))
                    .submit()
                    .get();
            if (bitmap != null) {
                rv.setImageViewBitmap(R.id.imageView, bitmap);
                Log.e("test", "test: OK");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.e("glide", e.getMessage());
        }
    }
}
