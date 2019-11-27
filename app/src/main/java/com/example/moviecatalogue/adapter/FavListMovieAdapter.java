package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.MovieDetailActivity;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.entity.FavMovie;
import com.example.moviecatalogue.entity.Movie;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavListMovieAdapter extends RecyclerView.Adapter<FavListMovieAdapter.ListViewHolder> {
    private List<FavMovie> favMovieList = new ArrayList<>();
    private Context context;

    public FavListMovieAdapter(Context context) {
        this.context = context;
    }

    public FavListMovieAdapter(Context context, List<FavMovie> favMovieList) {
        this.context = context;
        this.favMovieList = favMovieList;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private ImageView imgPoster;
        TextView tvTitle, tvOverview;

        ListViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            imgPoster = mView.findViewById(R.id.img_poster);
            tvTitle = mView.findViewById(R.id.tv_item_title);
            tvOverview = mView.findViewById(R.id.tv_item_description);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_row_favorite_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.tvTitle.setText(favMovieList.get(position).getTitle());
        holder.tvOverview.setText(favMovieList.get(position).getOverview());

        String urlGambar = "";

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);
    }

    public void setData(ArrayList<FavMovie> favMovies) {
        if(favMovieList == null) {
            favMovieList = new ArrayList<>();
        }
        else {
            favMovieList.clear();
        }
        favMovieList.addAll(favMovies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favMovieList.size();
    }
}