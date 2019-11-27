package com.example.moviecatalogue.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.MainViewModelFavMovie;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.FavListMovieAdapter;
import com.example.moviecatalogue.entity.FavMovie;

import java.util.ArrayList;

public class FavoriteMovieFragment extends Fragment {
    private FavListMovieAdapter adapter;
    ProgressDialog progressDialog;
    private RecyclerView rvfavMovies;
    private MainViewModelFavMovie mainViewModel;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvfavMovies = getView().findViewById(R.id.rv_favmovies);
        adapter = new FavListMovieAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvfavMovies.setLayoutManager(layoutManager);
        rvfavMovies.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelFavMovie.class);

        mainViewModel.setFavListMovies();

        mainViewModel.getFavListMovies().observe(this, new Observer<ArrayList<FavMovie>>(){
            @Override
            public void onChanged(ArrayList<FavMovie> favMovies) {
                if (favMovies != null) {
                    adapter.setData(favMovies);
                    progressDialog.dismiss();
                }
            }
        });
    }
}
