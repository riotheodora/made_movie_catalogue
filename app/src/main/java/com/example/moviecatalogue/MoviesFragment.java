package com.example.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        list.addAll(getListMovies());
        showRecyclerList();
    }

    public ArrayList<Movie> getListMovies() {
        String[] dataName = getResources().getStringArray(R.array.data_title_movies);
        String[] dataDescription = getResources().getStringArray(R.array.data_overview_movies);
        TypedArray dataPoster = getResources().obtainTypedArray(R.array.data_poster_movies);
        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataName[i]);
            movie.setOverview(dataDescription[i]);
            movie.setPoster(dataPoster.getResourceId(i, -1));
            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        ListMovieAdapter listHeroAdapter = new ListMovieAdapter(list);
        rvMovies.setAdapter(listHeroAdapter);
    }

}
