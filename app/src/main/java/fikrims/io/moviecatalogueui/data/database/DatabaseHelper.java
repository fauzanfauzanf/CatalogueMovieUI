/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 10/21/17 10:25 AM.
 */

package fikrims.io.moviecatalogueui.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "movie.db";

    public static String TABLE_FAVORITE = "favorite_movie";

    public static String FIELD_ID = "id";
    public static String FIELD_ADULT = "adult";
    public static String FIELD_BACKDROP_PATH = "backdrop_path";
    public static String FIELD_GENRE = "genre_ids";
    public static String FIELD_LANGUAGE = "original_language";
    public static String FIELD_TITLE_ORIGINAL = "original_title";
    public static String FIELD_OVERVIEW = "overview";
    public static String FIELD_POPULARITY = "popularity";
    public static String FIELD_POSTER = "poster_path";
    public static String FIELD_RELEASE = "release_date";
    public static String FIELD_TITLE = "title";
    public static String FIELD_VIDEO = "video";
    public static String FIELD_VOTE_AVERAGE = "vote_average";
    public static String FIELD_VOTE_COUNT = "vote_count";
    public static String FIELD_STATUS = "status";

    private static final int DATABASE_VERSION = 11;

    public static String CREATE_TABLE_FAVORITE = "create table " + TABLE_FAVORITE + " (" +
            FIELD_ID + " INTEGER primary key not null, " +
            FIELD_ADULT + " text , " +
            FIELD_BACKDROP_PATH + " text , " +
            FIELD_GENRE + " text , " +
            FIELD_LANGUAGE + " text , " +
            FIELD_TITLE_ORIGINAL + " text, " +
            FIELD_OVERVIEW + " text , " +
            FIELD_POPULARITY + " INTEGER , " +
            FIELD_POSTER + " text , " +
            FIELD_RELEASE + " text , " +
            FIELD_TITLE + " text , " +
            FIELD_VIDEO + " text, " +
            FIELD_VOTE_AVERAGE + " real , " +
            FIELD_VOTE_COUNT + " INTEGER , " +
            FIELD_STATUS + " INTEGER  );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(db);
    }
}
