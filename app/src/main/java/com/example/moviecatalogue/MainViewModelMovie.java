package com.example.moviecatalogue;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.entity.Movie;
import com.example.moviecatalogue.service.GetMovieDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModelMovie extends ViewModel {
    private static final String API_KEY = "ee8db69c065a14a15bb13e12ab61a116";
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

    public LiveData<ArrayList<Movie>> getListMovies() {
        return listMovies;
    }
}
