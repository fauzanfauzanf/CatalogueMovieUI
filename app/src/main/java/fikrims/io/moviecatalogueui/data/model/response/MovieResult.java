
package fikrims.io.moviecatalogueui.data.model.response;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static fikrims.io.moviecatalogueui.data.provider.DatabaseContract.getColumnDouble;
import static fikrims.io.moviecatalogueui.data.provider.DatabaseContract.getColumnInt;
import static fikrims.io.moviecatalogueui.data.provider.DatabaseContract.getColumnString;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_BACKDROP;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_ID;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_OVERVIEW;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_POSTER;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_RELEASE_DATE;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_STATUS;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_TITLE;
import static fikrims.io.moviecatalogueui.data.provider.FavoriteColumns.COLUMN_VOTE;

public class MovieResult implements Parcelable {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("id")
    private int mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;
    @SerializedName("status")
    private int status;

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        mGenreIds = genreIds;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mAdult);
        dest.writeString(this.mBackdropPath);
        dest.writeList(this.mGenreIds);
        dest.writeValue(this.mId);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeValue(this.mPopularity);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mTitle);
        dest.writeValue(this.mVideo);
        dest.writeValue(this.mVoteAverage);
        dest.writeValue(this.mVoteCount);
    }

    public MovieResult() {
    }

    protected MovieResult(Parcel in) {
        this.mAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mBackdropPath = in.readString();
        this.mGenreIds = new ArrayList<Long>();
        in.readList(this.mGenreIds, Long.class.getClassLoader());
        this.mId = (int) in.readValue(int.class.getClassLoader());
        this.mOriginalLanguage = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPopularity = (Double) in.readValue(Double.class.getClassLoader());
        this.mPosterPath = in.readString();
        this.mReleaseDate = in.readString();
        this.mTitle = in.readString();
        this.mVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.mVoteCount = (Long) in.readValue(Long.class.getClassLoader());
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

    public MovieResult(Cursor cursor) {
        this.mId = getColumnInt(cursor, COLUMN_ID);
        this.mTitle = getColumnString(cursor, COLUMN_TITLE);
        this.mBackdropPath = getColumnString(cursor, COLUMN_BACKDROP);
        this.mPosterPath = getColumnString(cursor, COLUMN_POSTER);
        this.mReleaseDate = getColumnString(cursor, COLUMN_RELEASE_DATE);
        this.mVoteAverage = getColumnDouble(cursor, COLUMN_VOTE);
        this.mOverview = getColumnString(cursor, COLUMN_OVERVIEW);
        this.status = getColumnInt(cursor, COLUMN_STATUS);
    }

    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        "overview = '" + mOverview + '\'' +
                        ",original_language = '" + mOriginalLanguage + '\'' +
                        ",original_title = '" + mOriginalTitle + '\'' +
                        ",video = '" + mVideo + '\'' +
                        ",title = '" + mTitle + '\'' +
                        ",genre_ids = '" + mGenreIds + '\'' +
                        ",poster_path = '" + mPosterPath + '\'' +
                        ",backdrop_path = '" + mBackdropPath + '\'' +
                        ",release_date = '" + mReleaseDate + '\'' +
                        ",vote_average = '" + mVoteAverage + '\'' +
                        ",popularity = '" + mPopularity + '\'' +
                        ",id = '" + mId + '\'' +
                        ",adult = '" + mAdult + '\'' +
                        ",vote_count = '" + mVoteCount + '\'' +
                        "}";
    }
}
