package com.example.mywatchlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mywatchlist.entity.Movie;

import java.util.ArrayList;

public class MainViewModelMovie extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public void setListMovies() {
    }

    public LiveData<ArrayList<Movie>> getListMovies() {
        return listMovies;
    }
}
