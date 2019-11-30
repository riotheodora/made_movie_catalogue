package com.example.moviecatalogue.service;

import com.example.moviecatalogue.entity.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieDataService {
    @GET("discover/movie?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<MovieResult> getAllMovies();

    @GET("search/movie?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<MovieResult> getSearchedMovies(@Query("query") String movie_name);

    @GET("discover/movie?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<MovieResult> getNewReleaseMovie(@Query("primary_release_date.gte") String today);
}