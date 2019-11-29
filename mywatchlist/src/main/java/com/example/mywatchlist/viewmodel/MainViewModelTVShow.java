package com.example.mywatchlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mywatchlist.entity.TVShow;

import java.util.ArrayList;

public class MainViewModelTVShow extends ViewModel {
    private MutableLiveData<ArrayList<TVShow>> listTVShow = new MutableLiveData<>();

    public void setListTVShows() {
    }

    public LiveData<ArrayList<TVShow>> getListTVShows() {
        return listTVShow;
    }
}
