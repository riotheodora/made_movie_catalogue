package com.example.moviecatalogue.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TVShowResult implements Parcelable {
    @SerializedName("results")
    private ArrayList<TVShow> results;

    private TVShowResult(Parcel in) {
        results = in.createTypedArrayList(TVShow.CREATOR);
    }

    public static final Creator<TVShowResult> CREATOR = new Creator<TVShowResult>() {
        @Override
        public TVShowResult createFromParcel(Parcel in) {
            return new TVShowResult(in);
        }

        @Override
        public TVShowResult[] newArray(int size) {
            return new TVShowResult[size];
        }
    };

    public ArrayList<TVShow> getResults() {
        return results;
    }

    public void setResults(ArrayList<TVShow> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }


}