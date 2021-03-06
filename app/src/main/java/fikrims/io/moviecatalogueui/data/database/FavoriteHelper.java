/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/11/17 3:14 PM.
 */

package fikrims.io.moviecatalogueui.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import fikrims.io.moviecatalogueui.data.provider.FavoriteColumns;

public class FavoriteHelper {

    private static String TABLE_NAME = FavoriteColumns.TABLE_NAME;

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public Cursor queryProvider() {
        return database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                FavoriteColumns.COLUMN_ID + " DESC"
        );
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(
                TABLE_NAME,
                null,
                FavoriteColumns.COLUMN_ID + " = ?",
                new String[]{id},
                null,
                null,
                null
        );
    }

    public long insertProvider(ContentValues values) {
        return database.insert(
                TABLE_NAME,
                null,
                values
        );
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(
                TABLE_NAME,
                values,
                FavoriteColumns.COLUMN_ID + " = ?",
                new String[]{id}
        );
    }

    public int deleteProvider(String id) {
        return database.delete(
                TABLE_NAME,
                FavoriteColumns.COLUMN_ID + " = ?",
                new String[]{id}
        );
    }
}
