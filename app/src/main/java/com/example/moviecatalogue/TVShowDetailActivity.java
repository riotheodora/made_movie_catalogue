package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class TVShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        TVShow tvshow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        String urlGambar = "https://image.tmdb.org/t/p/w185/" + tvshow.getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext()));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(ivPoster);

        tvTitle.setText(tvshow.getOriginal_name());
        tvOverview.setText(tvshow.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
