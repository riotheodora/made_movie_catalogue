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

public class ListTVShowAdapter extends RecyclerView.Adapter<ListTVShowAdapter.ListViewHolder> {
    private List<TVShow> listTVShow = new ArrayList<>();
    private Context context;

    public ListTVShowAdapter(Context context) {
        this.context = context;
    }

    public ListTVShowAdapter(Context context, List<TVShow> listTVShow) {
        this.context = context;
        this.listTVShow = listTVShow;
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
        View view = layoutInflater.inflate(R.layout.item_row_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.tvTitle.setText(listTVShow.get(position).getOriginal_name());
        holder.tvOverview.setText(listTVShow.get(position).getOverview());

        String urlGambar = "https://image.tmdb.org/t/p/w185/" + listTVShow.get(position).getPoster_path();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(urlGambar)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivityIntent = new Intent(context, TVShowDetailActivity.class);
                detailActivityIntent.putExtra(TVShowDetailActivity.EXTRA_TVSHOW, listTVShow.get(position));
                context.startActivity(detailActivityIntent);
            }
        });
    }

    public void setData(ArrayList<TVShow> tvshows) {
        if(listTVShow == null) {
            listTVShow = new ArrayList<>();
        }
        else {
            listTVShow.clear();
        }
        listTVShow.addAll(tvshows);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listTVShow.size();
    }
}