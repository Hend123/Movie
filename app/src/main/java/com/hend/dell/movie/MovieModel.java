package com.hend.dell.movie;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

class MovieModel implements  Parcelable {
    private String poster_path;
    private String original_title;
    private String overview;
    private String release_date;
    private double vote_average;
    private double popularity;


    protected MovieModel(Parcel in) {
        poster_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readDouble();
        popularity = in.readDouble();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public static Uri buildUrl(String posterPath) {
        Uri url = null;

        Uri uri = Uri.parse("https://image.tmdb.org").buildUpon().path("/t/p/w500" + posterPath).build();
        return uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeDouble(vote_average);
        dest.writeDouble(popularity);
    }
}
