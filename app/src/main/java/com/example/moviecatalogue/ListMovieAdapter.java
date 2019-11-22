package com.example.moviecatalogue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private List<Movie> listMovie = new ArrayList<>();
    private Context context;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    public ListMovieAdapter(Context context, List<Movie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
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
        View view = layoutInflater.inflate(R.layout.item_row_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvOverview.setText(listMovie.get(position).getOverview());

        String urlGambar = "https://image.tmdb.org/t/p/w185/" + listMovie.get(position).getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivityIntent = new Intent(context, MovieDetailActivity.class);
                detailActivityIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, listMovie.get(position));
                context.startActivity(detailActivityIntent);
            }
        });
    }

    public void setData(ArrayList<Movie> movies) {
        if(listMovie == null) {
            listMovie = new ArrayList<>();
        }
        else {
            listMovie.clear();
        }
        listMovie.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }
}