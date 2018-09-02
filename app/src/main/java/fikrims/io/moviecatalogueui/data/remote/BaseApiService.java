package fikrims.io.moviecatalogueui.data.remote;

import fikrims.io.moviecatalogueui.data.model.response.Movie;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("movie/now_playing")
    Flowable<Movie> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Flowable<Movie> getUpcomingMovie(@Query("api_key") String apiKey);

    @GET("search/movie/")
    Flowable<Movie> getMovieBySearch(@Query("query") String query, @Query("api_key") String apiKey);
}
