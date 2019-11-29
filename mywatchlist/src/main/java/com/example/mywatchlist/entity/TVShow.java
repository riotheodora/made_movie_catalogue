package com.example.mywatchlist.entity;

public class TVShow {

    private Integer id;
    private String original_name;
    private String overview;
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

    public TVShow(int id, String title, String overview, String poster_path) {
        this.id = id;
        this.original_name = title;
        this.overview = overview;
        this.poster_path = poster_path;
    }
}