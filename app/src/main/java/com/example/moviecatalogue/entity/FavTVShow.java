package com.example.moviecatalogue.entity;

import com.google.gson.annotations.SerializedName;

public class FavTVShow {

    @SerializedName("id")
    private Integer id;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String poster_path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public FavTVShow(int id, String title, String overview, String poster_path) {
        this.id = id;
        this.original_name = title;
        this.overview = overview;
        this.poster_path = poster_path;
    }
}