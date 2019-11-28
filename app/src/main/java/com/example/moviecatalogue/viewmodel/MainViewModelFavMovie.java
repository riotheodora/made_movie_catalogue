package com.example.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecatalogue.db.FavMovieHelper;
import com.example.moviecatalogue.entity.FavMovie;
import com.example.moviecatalogue.helper.MappingHelper;

import java.util.ArrayList;

public class MainViewModelFavMovie extends AndroidViewModel {
    private MutableLiveData<ArrayList<FavMovie>> listMovies = new MutableLiveData<>();
    private FavMovieHelper favMovieHelper;

    public MainViewModelFavMovie(@NonNull Application application) {
        super(application);
    }

    public void setFavListMovies() {
        favMovieHelper = FavMovieHelper.getInstance(getApplication().getApplicationContext());
        favMovieHelper.queryAll();
        ArrayList<FavMovie> favMovies = MappingHelper.mapCursorToArrayList(favMovieHelper.queryAll());
        listMovies.setValue(favMovies);
    }

    public LiveData<ArrayList<FavMovie>> getFavListMovies() {
        return listMovies;
    }
}