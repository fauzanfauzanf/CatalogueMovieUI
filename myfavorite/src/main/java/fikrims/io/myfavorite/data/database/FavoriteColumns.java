/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/11/17 3:14 PM.
 */

package fikrims.io.myfavorite.data.database;

import android.provider.BaseColumns;

public class FavoriteColumns implements BaseColumns {
    public static String TABLE_NAME = "favorite_movie";

    public static String COLUMN_ID = "id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_BACKDROP = "backdrop_path";
    public static String COLUMN_POSTER = "poster_path";
    public static String COLUMN_RELEASE_DATE = "release_date";
    public static String COLUMN_VOTE = "vote_average";
    public static String COLUMN_OVERVIEW = "overview";
    public static String COLUMN_STATUS = "status";
}
