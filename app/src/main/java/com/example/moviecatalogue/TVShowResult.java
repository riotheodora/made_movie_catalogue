package com.example.moviecatalogue;

import com.example.moviecatalogue.entity.TVShow;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TVShowResult {
    @SerializedName("results")
    private ArrayList<TVShow> results;

    public ArrayList<TVShow> getResults() {
        return results;
    }

    public void setResults(ArrayList<TVShow> results) {
        this.results = results;
    }
}