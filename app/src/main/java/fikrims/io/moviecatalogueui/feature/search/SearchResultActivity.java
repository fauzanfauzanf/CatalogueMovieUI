package fikrims.io.moviecatalogueui.feature.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import fikrims.io.moviecatalogueui.data.remote.UtilsApi;
import fikrims.io.moviecatalogueui.utils.Constant;

public class SearchResultActivity extends AppCompatActivity implements SearchMoviePresenter.SearchMovieListener{

    @BindView(R.id.recycler_search)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_search)
    Toolbar toolbar;

    private int columns = 0;
    private Context context;
    private SearchMovieAdapter searchMovieAdapter;
    private BaseApiService mApiService;
    private SearchMoviePresenter searchMoviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = SearchResultActivity.this;
        if(getIntent() != null){
            String search = getIntent().getStringExtra(Constant.Utils.INTENT_SEARCH);
            initView();
            getMovies(search);
        }
    }

    void initView(){
        columns = getResources().getInteger(R.integer.collumn_count);
        searchMovieAdapter = new SearchMovieAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    void getMovies(String search){
        mApiService = UtilsApi.getAPIService(context);
        searchMoviePresenter = new SearchMoviePresenter(context, mApiService, this);
        searchMoviePresenter.doMovie(search);
    }

    @Override
    public void getMovie(List<MovieResult> listMovie) {
        searchMovieAdapter.setMovieResult(listMovie);
        recyclerView.setAdapter(searchMovieAdapter);
    }
}
