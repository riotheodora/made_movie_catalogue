package com.example.moviecatalogue.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.viewmodel.MainViewModelFavTVShow;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.FavListTVShowAdapter;
import com.example.moviecatalogue.entity.FavTVShow;

import java.util.ArrayList;

public class FavoriteTVShowFragment extends Fragment {
    private FavListTVShowAdapter adapter;
    private ProgressDialog progressDialog;
    private RecyclerView rvfavTVShows;
    private MainViewModelFavTVShow mainViewModel;

    public FavoriteTVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvfavTVShows = getView().findViewById(R.id.rv_favtvshows);
        adapter = new FavListTVShowAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvfavTVShows.setLayoutManager(layoutManager);
        rvfavTVShows.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModelFavTVShow.class);

        mainViewModel.setFavListTVShows();

        mainViewModel.getFavListTVShows().observe(this, new Observer<ArrayList<FavTVShow>>(){
            @Override
            public void onChanged(ArrayList<FavTVShow> favTVShows) {
                if (favTVShows != null) {
                    adapter.setData(favTVShows);
                    progressDialog.dismiss();
                }
            }
        });
    }
}
