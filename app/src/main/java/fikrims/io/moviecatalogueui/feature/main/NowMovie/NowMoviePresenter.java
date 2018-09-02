package fikrims.io.moviecatalogueui.feature.main.NowMovie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static fikrims.io.moviecatalogueui.utils.Constant.Utils.API_KEY;


@SuppressLint("CheckResult")
public class NowMoviePresenter {

    private final Context context;
    private BaseApiService mApiService;
    private final NowMovieListener mListener;

    public NowMoviePresenter(Context context, BaseApiService mApiService, NowMovieListener mListener){

        this.context = context;
        this.mApiService = mApiService;
        this.mListener = mListener;
    }

    public interface NowMovieListener{
        void getMovie(List<MovieResult> listMovie);
    }

    void doMovie(){
        mApiService
                .getNowPlayingMovie(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> mListener.getMovie(data.getResults())
                ,throwable -> Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show());
    }
}
