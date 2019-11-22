package com.example.moviecatalogue;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetTVShowDataService {
    @GET("tv?api_key=ee8db69c065a14a15bb13e12ab61a116&language=en-US")
    Call<TVShowResult> getAllTVShows();
}

