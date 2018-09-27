package fikrims.io.myfavorite.feature.main;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fikrims.io.myfavorite.data.provider.FavoriteModel;

import static fikrims.io.myfavorite.data.provider.DatabaseContract.CONTENT_URI;

public class MainPresenter {

    private final Context context;
    private final MainListener listener;
    private Cursor list;

    public MainPresenter(Context context, MainListener listener ) {
        this.context = context;
        this.listener = listener;
    }

    public interface MainListener{
        void getMovie(Cursor cursor);
    }

    void doMovie(){
        List<FavoriteModel> movies = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            do {
                listener.getMovie(cursor);
                cursor.moveToNext();
            } while(!cursor.isAfterLast());
        }

//        cursor.close();
    }
}
