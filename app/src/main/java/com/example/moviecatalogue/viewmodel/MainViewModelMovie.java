package com.example.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.RetrofitClientInstance;
import com.example.moviecatalogue.entity.Movie;
import com.example.moviecatalogue.entity.MovieResult;
import com.example.moviecatalogue.service.GetMovieDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelMovie extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public void setListMovies() {
        GetMovieDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetMovieDataService.class);
        Call<MovieResult> call = service.getAllMovies();
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult movieResult = response.body();

                if(movieResult.getResults().size() > 0) {
                    listMovies.setValue(movieResult.getResults());
                }
                else {
                    listMovies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
            }
        });
    }

    public void setSpecificListMovies(String query) {
        GetMovieDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetMovieDataService.class);
        Call<MovieResult> call = service.getSearchedMovies(query);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult movieResult = response.body();

                if(movieResult.getResults().size() > 0) {
                    listMovies.setValue(movieResult.getResults());
                    Log.e("ISI PERTAMA", movieResult.getResults().get(0).getTitle());
                }
                else {
                    listMovies.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e("STATE", "onFailure");
            }
        });
    }

    public LiveData<ArrayList<Movie>> getListMovies() {
        return listMovies;
    }
}
