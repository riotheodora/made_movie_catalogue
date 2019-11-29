package com.example.mywatchlist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mywatchlist.entity.TVShow;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class TVShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    TVShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivPoster = findViewById(R.id.iv_poster_received);
        TextView tvTitle = findViewById(R.id.tv_title_received);
        TextView tvOverview = findViewById(R.id.tv_overview_received);

        tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        String urlGambar = "https://image.tmdb.org/t/p/w185/" + tvShow.getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext()));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(ivPoster);

        tvTitle.setText(tvShow.getOriginal_name());
        tvOverview.setText(tvShow.getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
