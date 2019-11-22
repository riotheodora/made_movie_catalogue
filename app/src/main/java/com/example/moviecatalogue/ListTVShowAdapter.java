package com.example.moviecatalogue;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListTVShowAdapter extends RecyclerView.Adapter<ListTVShowAdapter.ListViewHolder> {
    private ArrayList<TVShow> listTVShow;

    public ListTVShowAdapter(ArrayList<TVShow> list) {
        this.listTVShow = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        TVShow tvshow = listTVShow.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tvshow.getPoster())
                .apply(new RequestOptions().override(300, 450))
                .into(holder.imgPoster);

        holder.tvTitle.setText(tvshow.getTitle());
        holder.tvOverview.setText(tvshow.getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TVShow tvshow = new TVShow();
                tvshow.setTitle(listTVShow.get(holder.getAdapterPosition()).getTitle());
                tvshow.setOverview(listTVShow.get(holder.getAdapterPosition()).getOverview());
                tvshow.setPoster(listTVShow.get(holder.getAdapterPosition()).getPoster());

                Intent detailActivityIntent = new Intent(view.getContext(), TVShowDetailActivity.class);
                detailActivityIntent.putExtra(TVShowDetailActivity.EXTRA_TVSHOW, tvshow);
                view.getContext().startActivity(detailActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTVShow.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvOverview;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvOverview = itemView.findViewById(R.id.tv_item_description);
        }
    }
}
