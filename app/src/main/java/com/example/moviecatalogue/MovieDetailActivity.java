package com.example.moviecatalogue;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.moviecatalogue.db.FavMovieHelper;
import com.example.moviecatalogue.db.MovieDatabaseContract;
import com.example.moviecatalogue.entity.Movie;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private FavMovieHelper favMovieHelper;
    Movie  movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String urlGambar = "https://image.tmdb.org/t/p/w185/" + movie.getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext()));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(ivPoster);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_favorite){
            favMovieHelper = FavMovieHelper.getInstance(getApplicationContext());
            favMovieHelper.open();

            ContentValues contentValues = new ContentValues();

            contentValues.put(MovieDatabaseContract.MovieColumns._ID, movie.getId());
            contentValues.put(MovieDatabaseContract.MovieColumns.TITLE, movie.getTitle() );
            contentValues.put(MovieDatabaseContract.MovieColumns.OVERVIEW, movie.getOverview());
            contentValues.put(MovieDatabaseContract.MovieColumns.POSTER, movie.getPoster_path());

            favMovieHelper.insert(contentValues);
        }
        return super.onOptionsItemSelected(item);
    }
}
