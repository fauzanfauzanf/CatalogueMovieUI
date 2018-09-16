package fikrims.io.moviecatalogueui.data.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fikrims.io.moviecatalogueui.data.model.response.MovieResult;

public class MovieHelper {

    private static String FAVORITE = DatabaseHelper.TABLE_FAVORITE;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    @SuppressLint("Recycle")
    public List<MovieResult> getFavoriteMovie(){
        MovieResult movieResult;

        List<MovieResult> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + FAVORITE, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            do {
                movieResult = new MovieResult();

                movieResult.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                movieResult.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_POSTER)));
                movieResult.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_BACKDROP_PATH)));
                movieResult.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TITLE)));
                movieResult.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_OVERVIEW)));
                movieResult.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_RELEASE)));
                movieResult.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_VOTE_AVERAGE)));

                list.add(movieResult);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return list;
    }

    public void insert(MovieResult movieResult, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FIELD_ID, movieResult.getId());
        contentValues.put(DatabaseHelper.FIELD_POSTER, movieResult.getPosterPath());
        contentValues.put(DatabaseHelper.FIELD_BACKDROP_PATH, movieResult.getBackdropPath());
        contentValues.put(DatabaseHelper.FIELD_TITLE, movieResult.getTitle());
        contentValues.put(DatabaseHelper.FIELD_OVERVIEW, movieResult.getOverview());
        contentValues.put(DatabaseHelper.FIELD_RELEASE, movieResult.getReleaseDate());
        contentValues.put(DatabaseHelper.FIELD_VOTE_AVERAGE, movieResult.getVoteAverage());
        contentValues.put(DatabaseHelper.FIELD_STATUS, status);

        database.insert(FAVORITE, null, contentValues);
    }

    public void delete(long id) {
        database.delete(FAVORITE, DatabaseHelper.FIELD_ID + " = '" + id + "'", null);
    }
}
