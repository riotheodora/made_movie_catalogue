package com.example.moviecatalogue.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.RetrofitClientInstance;
import com.example.moviecatalogue.entity.TVShowResult;
import com.example.moviecatalogue.entity.TVShow;
import com.example.moviecatalogue.service.GetTVShowDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelTVShow extends ViewModel {
    private MutableLiveData<ArrayList<TVShow>> listTVShow = new MutableLiveData<>();

    public void setListTVShows() {
        GetTVShowDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetTVShowDataService.class);
        Call<TVShowResult> call = service.getAllTVShows();
        call.enqueue(new Callback<TVShowResult>() {
            @Override
            public void onResponse(Call<TVShowResult> call, Response<TVShowResult> response) {
                TVShowResult tvShowResult = response.body();

                if(tvShowResult.getResults().size() > 0) {
                    listTVShow.setValue(tvShowResult.getResults());
                }
                else {
                    listTVShow.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<TVShowResult> call, Throwable t) {
            }
        });
    }

    public LiveData<ArrayList<TVShow>> getListTVShows() {
        return listTVShow;
    }
}
