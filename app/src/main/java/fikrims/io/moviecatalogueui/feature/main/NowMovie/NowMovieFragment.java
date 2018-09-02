package fikrims.io.moviecatalogueui.feature.main.NowMovie;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import fikrims.io.moviecatalogueui.data.remote.UtilsApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowMovieFragment extends Fragment implements NowMoviePresenter.NowMovieListener {

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    private NowMovieAdapter nowMovieAdapter;
    private BaseApiService mApiService;
    private NowMoviePresenter nowMoviePresenter;
    private Context context;
    private int columns;

    public NowMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_movie, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        mApiService = UtilsApi.getAPIService(context);
        nowMoviePresenter = new NowMoviePresenter(context, mApiService, this);
        nowMoviePresenter.doMovie();
        initView();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);

        // show data in recyclerview

        swipeRefreshLayout.setOnRefreshListener(() -> {

            nowMovieAdapter.notifyDataSetChanged();
            nowMoviePresenter.doMovie();

        });

        return view;
    }

    void initView(){
        columns = getResources().getInteger(R.integer.collumn_count);
        nowMovieAdapter = new NowMovieAdapter(getContext());
        listMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovie.setHasFixedSize(true);
        listMovie.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void getMovie(List<MovieResult> list) {
        nowMovieAdapter.setMovieResult(list);
        listMovie.setAdapter(nowMovieAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}
