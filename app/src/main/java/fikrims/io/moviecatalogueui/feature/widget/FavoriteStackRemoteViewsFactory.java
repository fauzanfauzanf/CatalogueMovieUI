package fikrims.io.moviecatalogueui.feature.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.feature.detail_movie.DetailActivity;

import static android.content.ContentValues.TAG;
import static fikrims.io.moviecatalogueui.data.provider.DatabaseContract.CONTENT_URI;

public class FavoriteStackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private Context context;
    private int mAppWidgetId;
    private Cursor cursor;

    public FavoriteStackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {

        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        if (cursor == null) return 0;
        else return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                cursor == null || !cursor.moveToPosition(position)) {
            return null;
        }

        MovieResult movie = getItem(position);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.movie_grid_item);

        Bitmap bmp = null;
        try {

            bmp = Glide.with(context)
                    .asBitmap()
                    .load("http://image.tmdb.org/t/p/w185/"+ movie.getPosterPath())
                    //.error(new ColorDrawable(context.getResources().getColor(R.color.colorPrimary)))
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

        } catch (InterruptedException | ExecutionException e) {
            Log.d("Widget Load Error", "error");
        }

        Log.e(TAG, "getViewAt: " + movie.getTitle());

        rv.setImageViewBitmap(R.id.movie_poster, bmp);
        rv.setTextViewText(R.id.tv_movie_title, movie.getTitle());

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent(context, DetailActivity.class);
        fillInIntent.putExtras(extras);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, fillInIntent, 0);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        rv.setOnClickPendingIntent(R.id.movie_poster, configPendingIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return cursor.moveToPosition(position) ? cursor.getLong(0) : position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    private MovieResult getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new MovieResult(cursor);
    }
}
