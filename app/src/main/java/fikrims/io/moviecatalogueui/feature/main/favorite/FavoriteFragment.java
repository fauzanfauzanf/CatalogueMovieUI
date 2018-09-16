package fikrims.io.moviecatalogueui.feature.main.favorite;


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
import fikrims.io.moviecatalogueui.data.database.MovieHelper;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoritePresenter.FavoriteListener {

    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefresh;

    private FavoriteAdapter favoriteAdapter;
    private FavoritePresenter favoritePresenter;
    private Context context;
    private MovieHelper movieHelper;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        movieHelper = new MovieHelper(context);
        favoritePresenter = new FavoritePresenter(context, this, movieHelper);
        initView();

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setRefreshing(true);

        favoritePresenter.doMovie();
        // show data in recyclerview

        swipeRefresh.setOnRefreshListener(() -> {

            favoriteAdapter.notifyDataSetChanged();
            favoritePresenter.doMovie();

        });

        return view;
    }

    void initView(){
        favoriteAdapter = new FavoriteAdapter(getContext());
        listMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovie.setHasFixedSize(true);
        listMovie.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void getMovie(List<MovieResult> list) {
        favoriteAdapter.setMovieResult(list);
        listMovie.setAdapter(favoriteAdapter);
        swipeRefresh.setRefreshing(false);
    }

    public void refresh(){
        swipeRefresh.setRefreshing(true);
        favoritePresenter.doMovie();
    }
}
