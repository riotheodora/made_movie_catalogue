package com.example.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviecatalogue.entity.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResult implements Parcelable {
    @SerializedName("results")
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

    protected MovieResult(Parcel in) {
        this.results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Parcelable.Creator<MovieResult> CREATOR = new Parcelable.Creator<MovieResult>() {
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
