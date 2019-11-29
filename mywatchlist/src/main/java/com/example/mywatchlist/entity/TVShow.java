package com.example.mywatchlist.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.original_name);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster_path);
    }

    private TVShow(Parcel in) {
        this.id = in.readInt();
        this.original_name = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}