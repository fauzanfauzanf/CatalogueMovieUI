package fikrims.io.moviecatalogueui.feature.main.UpcomingMovie;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import fikrims.io.moviecatalogueui.data.remote.UtilsApi;

import static fikrims.io.moviecatalogueui.utils.Constant.Key.KEY_MOVIES_UPCOMING;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMovieFragment extends Fragment implements UpcomingMoviePresenter.UpcomingMovieListener {

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    private UpcomingMovieAdapter upcomingMovieAdapter;
    private BaseApiService mApiService;
    private UpcomingMoviePresenter upcomingMoviePresenter;
    private Context context;
    private int columns;
    private List<MovieResult> movies = new ArrayList<>();

    public UpcomingMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_movie, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        mApiService = UtilsApi.getAPIService(context);
        upcomingMoviePresenter = new UpcomingMoviePresenter(context, mApiService, this);

        initView();

        if (savedInstanceState == null) {
            upcomingMoviePresenter.doMovieUpcoming();
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES_UPCOMING);
            upcomingMovieAdapter.setMovieResult(movies);
            listMovie.setAdapter(upcomingMovieAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    void initView(){
        columns = getResources().getInteger(R.integer.collumn_count);
        upcomingMovieAdapter = new UpcomingMovieAdapter(getContext());
        listMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovie.setHasFixedSize(true);
        listMovie.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);

        // show data in recyclerview

        swipeRefreshLayout.setOnRefreshListener(() -> {

            upcomingMovieAdapter.notifyDataSetChanged();
            upcomingMoviePresenter.doMovieUpcoming();

        });
    }

    @Override
    public void getMovie(List<MovieResult> list) {
        movies = list;
        upcomingMovieAdapter.setMovieResult(list);
        listMovie.setAdapter(upcomingMovieAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES_UPCOMING, (ArrayList<? extends Parcelable>) movies);
        super.onSaveInstanceState(outState);
    }
}
