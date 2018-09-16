package fikrims.io.moviecatalogueui.feature.main.favorite;

import android.content.Context;

import java.util.List;

import fikrims.io.moviecatalogueui.data.database.MovieHelper;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;

public class FavoritePresenter {

    private final Context context;
    private final FavoriteListener listener;
    private final MovieHelper movieHelper;

    public FavoritePresenter(Context context, FavoriteListener listener, MovieHelper movieHelper) {
        this.context = context;
        this.listener = listener;
        this.movieHelper = movieHelper;
    }

    public interface FavoriteListener{
        void getMovie(List<MovieResult> list);
    }

    void doMovie(){
        movieHelper.open();
        listener.getMovie(movieHelper.getFavoriteMovie());
        movieHelper.close();
    }
}
