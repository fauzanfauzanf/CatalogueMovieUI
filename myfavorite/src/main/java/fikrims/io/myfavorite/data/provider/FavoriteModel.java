/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/11/17 3:14 PM.
 */

package fikrims.io.myfavorite.data.provider;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_BACKDROP;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_ID;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_OVERVIEW;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_POSTER;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_RELEASE_DATE;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_STATUS;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_TITLE;
import static fikrims.io.myfavorite.data.database.FavoriteColumns.COLUMN_VOTE;
import static fikrims.io.myfavorite.data.provider.DatabaseContract.getColumnDouble;
import static fikrims.io.myfavorite.data.provider.DatabaseContract.getColumnInt;
import static fikrims.io.myfavorite.data.provider.DatabaseContract.getColumnString;

/**
 * Created by omrobbie on 11/11/2017.
 */

public class FavoriteModel {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("status")
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FavoriteModel() {
    }

    public FavoriteModel(Cursor cursor) {
        this.id = getColumnInt(cursor, COLUMN_ID);
        this.title = getColumnString(cursor, COLUMN_TITLE);
        this.backdropPath = getColumnString(cursor, COLUMN_BACKDROP);
        this.posterPath = getColumnString(cursor, COLUMN_POSTER);
        this.releaseDate = getColumnString(cursor, COLUMN_RELEASE_DATE);
        this.voteAverage = getColumnDouble(cursor, COLUMN_VOTE);
        this.overview = getColumnString(cursor, COLUMN_OVERVIEW);
        this.status = getColumnInt(cursor, COLUMN_STATUS);
    }

    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        ",id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",overview = '" + overview + '\'' +
                        "status = '" + status + '\'' +
                        "}";
    }
}
