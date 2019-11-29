package com.example.mywatchlist.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieResult implements Parcelable {
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.results);
    }

    public MovieResult() {
    }

    private MovieResult(Parcel in) {
        this.results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel source) {
            return new MovieResult(source);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };
}
