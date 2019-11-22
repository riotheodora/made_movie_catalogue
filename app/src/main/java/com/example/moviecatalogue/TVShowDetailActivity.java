package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TVShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        TVShow tvshow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        ivPoster.setImageResource(tvshow.getPoster());
        tvTitle.setText(tvshow.getTitle());
        tvOverview.setText(tvshow.getOverview());
    }
}
