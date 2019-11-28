package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.db.FavMovieHelper;
import com.example.moviecatalogue.entity.FavMovie;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavListMovieAdapter extends RecyclerView.Adapter<FavListMovieAdapter.ListViewHolder> {
    private List<FavMovie> favMovieList = new ArrayList<>();
    private Context context;
    private FavMovieHelper favMovieHelper;

    public FavListMovieAdapter(Context context) {
        this.context = context;
    }

    public FavListMovieAdapter(Context context, List<FavMovie> favMovieList) {
        this.context = context;
        this.favMovieList = favMovieList;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        final View mView;

        private ImageView imgPoster;
        TextView tvTitle, tvOverview;
        Button removeButton;

        ListViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            imgPoster = mView.findViewById(R.id.img_poster);
            tvTitle = mView.findViewById(R.id.tv_item_title);
            tvOverview = mView.findViewById(R.id.tv_item_description);
            removeButton = mView.findViewById(R.id.btn_remove);
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
        favMovieHelper = FavMovieHelper.getInstance(null);

        holder.tvTitle.setText(favMovieList.get(position).getTitle());
        holder.tvOverview.setText(favMovieList.get(position).getOverview());

        String uri = "https://image.tmdb.org/t/p/w185/" + favMovieList.get(position).getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(uri)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favMovieHelper.deleteById(String.valueOf(favMovieList.get(position).getId()));
                favMovieList.remove(position);
                notifyDataSetChanged();
            }
        });
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