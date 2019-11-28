package com.example.moviecatalogue.service;

import com.example.moviecatalogue.entity.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetMovieDataService {
    @GET("movie?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<MovieResult> getAllMovies();

    @GET("movie?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US&query={keyword}")
    Call<MovieResult> getSearchedMovies(@Path("keyword") String path);
}