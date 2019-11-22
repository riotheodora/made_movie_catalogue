package com.example.moviecatalogue;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        //ivPoster.setImageURI(movie.getPoster_path());
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
    }
}
