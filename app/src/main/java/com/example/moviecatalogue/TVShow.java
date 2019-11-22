package com.example.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String title;
    private String overview;
    private int poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeInt(this.poster);
    }

    TVShow(){

    }

    protected TVShow(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = in.readInt();
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
