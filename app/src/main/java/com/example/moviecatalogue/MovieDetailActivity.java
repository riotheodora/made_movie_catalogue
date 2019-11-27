package com.example.moviecatalogue;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

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
}
