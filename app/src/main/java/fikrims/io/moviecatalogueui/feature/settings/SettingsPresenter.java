package fikrims.io.moviecatalogueui.feature.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static fikrims.io.moviecatalogueui.utils.Constant.Utils.API_KEY;

@SuppressLint("CheckResult")
public class SettingsPresenter {

    private final Context context;
    private BaseApiService mApiService;
    private final SettingsListener mListener;

    public SettingsPresenter(Context context, BaseApiService mApiService, SettingsListener mListener) {
        this.context = context;
        this.mApiService = mApiService;
        this.mListener = mListener;
    }

    public interface SettingsListener{
        void getMovie(MovieResult listMovie);

        void cancelAlarm();

        void error(Throwable throwable);
    }

    void doMovieUpcoming(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        Log.i("currentDate", currentDate);

        mApiService
                .getUpcomingMovie(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            for (MovieResult movie : data.getResults()) {
                                if (movie.getReleaseDate().equals(currentDate)) {
                                    mListener.getMovie(movie);
                                }
                            }
                        }
                        ,throwable -> {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                            mListener.error(throwable);
                        });
    }

    void cancelAlarmNotif(){
        Toast.makeText(context, "Disable Notification", Toast.LENGTH_LONG).show();
        mListener.cancelAlarm();
    }
}
