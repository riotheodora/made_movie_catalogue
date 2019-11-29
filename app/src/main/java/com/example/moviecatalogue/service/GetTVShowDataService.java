package com.example.moviecatalogue.service;

import com.example.moviecatalogue.entity.TVShowResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTVShowDataService {
    @GET("discover/tv?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<TVShowResult> getAllTVShows();

    @GET("search/tv?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<TVShowResult> getSearchedTVShows(@Query("query") String tv_show_name);
}

