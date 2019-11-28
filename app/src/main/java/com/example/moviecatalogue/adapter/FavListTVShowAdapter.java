package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.entity.FavMovie;
import com.example.moviecatalogue.entity.FavTVShow;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavListTVShowAdapter extends RecyclerView.Adapter<FavListTVShowAdapter.ListViewHolder> {
    private List<FavTVShow> favTVShowList = new ArrayList<>();
    private Context context;

    public FavListTVShowAdapter(Context context) {
        this.context = context;
    }

    public FavListTVShowAdapter(Context context, List<FavTVShow> favTVShowList) {
        this.context = context;
        this.favTVShowList = favTVShowList;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        final View mView;

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
        View view = layoutInflater.inflate(R.layout.item_row_favorite_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.tvTitle.setText(favTVShowList.get(position).getOriginal_name());
        holder.tvOverview.setText(favTVShowList.get(position).getOverview());

        String uri = "https://image.tmdb.org/t/p/w185/" + favTVShowList.get(position).getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(uri)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);
    }

    public void setData(ArrayList<FavTVShow> favTVShows) {
        if(favTVShowList == null) {
            favTVShowList = new ArrayList<>();
        }
        else {
            favTVShowList.clear();
        }
        favTVShowList.addAll(favTVShows);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favTVShowList.size();
    }
}