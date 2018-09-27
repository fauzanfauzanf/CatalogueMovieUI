/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 10/21/17 10:25 AM.
 */

package fikrims.io.moviecatalogueui.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fikrims.io.moviecatalogueui.data.provider.FavoriteColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "movie";

    public static String TABLE_FAVORITE = "favorite_movie";

    public static String FIELD_ID = "id";
    public static String FIELD_BACKDROP_PATH = "backdrop_path";
    public static String FIELD_OVERVIEW = "overview";
    public static String FIELD_POSTER = "poster_path";
    public static String FIELD_RELEASE = "release_date";
    public static String FIELD_TITLE = "title";
    public static String FIELD_VOTE_AVERAGE = "vote_average";
    public static String FIELD_STATUS = "status";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_FAVORITE = "create table " + FavoriteColumns.TABLE_NAME + " (" +
            FIELD_ID + " INTEGER primary key not null, " +
            FIELD_BACKDROP_PATH + " text , " +
            FIELD_OVERVIEW + " text , " +
            FIELD_POSTER + " text , " +
            FIELD_RELEASE + " text , " +
            FIELD_TITLE + " text , " +
            FIELD_VOTE_AVERAGE + " real , " +
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
