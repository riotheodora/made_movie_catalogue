package com.example.moviecatalogue;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecatalogue.db.FavTVShowHelper;
import com.example.moviecatalogue.entity.FavTVShow;
import com.example.moviecatalogue.helper.MappingHelper;

import java.util.ArrayList;

public class MainViewModelFavTVShow extends AndroidViewModel {
    private MutableLiveData<ArrayList<FavTVShow>> listTVShow = new MutableLiveData<>();
    private FavTVShowHelper favShowHelper;

    public MainViewModelFavTVShow(@NonNull Application application) {
        super(application);
    }

    public void setFavListTVShows() {
        favShowHelper = FavTVShowHelper.getInstance(getApplication().getApplicationContext());
        favShowHelper.queryAll();
        ArrayList<FavTVShow> favTVShows = MappingHelper.mapFavShowCursorToArrayList(favShowHelper.queryAll());
        listTVShow.setValue(favTVShows);
    }

    public LiveData<ArrayList<FavTVShow>> getFavListTVShows() {
        return listTVShow;
    }
}