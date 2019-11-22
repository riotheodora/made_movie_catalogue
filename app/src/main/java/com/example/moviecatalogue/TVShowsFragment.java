package com.example.moviecatalogue;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TVShowsFragment extends Fragment {
    private RecyclerView rvTVShows;
    private ArrayList<TVShow> list = new ArrayList<>();

    public TVShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTVShows = view.findViewById(R.id.rv_tvshows);
        rvTVShows.setHasFixedSize(true);

        list.addAll(getListTVShows());
        showRecyclerList();
    }

    public ArrayList<TVShow> getListTVShows() {
        String[] dataName = getResources().getStringArray(R.array.data_title_tvshows);
        String[] dataDescription = getResources().getStringArray(R.array.data_overview_tvshows);
        TypedArray dataPoster = getResources().obtainTypedArray(R.array.data_poster_tvshows);
        ArrayList<TVShow> listTVShow = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            TVShow tvshow = new TVShow();
            tvshow.setTitle(dataName[i]);
            tvshow.setOverview(dataDescription[i]);
            tvshow.setPoster(dataPoster.getResourceId(i, -1));
            listTVShow.add(tvshow);
        }
        return listTVShow;
    }

    private void showRecyclerList(){
        rvTVShows.setLayoutManager(new LinearLayoutManager(getContext()));
        ListTVShowAdapter listTVShowAdapter = new ListTVShowAdapter(list);
        rvTVShows.setAdapter(listTVShowAdapter);
    }
}
