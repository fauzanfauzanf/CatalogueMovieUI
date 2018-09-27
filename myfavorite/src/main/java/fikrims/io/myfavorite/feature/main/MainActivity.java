package fikrims.io.myfavorite.feature.main;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.myfavorite.R;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainListener {

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.list_movie)
    RecyclerView recyclerMovie;

    private Context context;
    private MainAdapter mainAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;

        initView();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefreshLayout.setRefreshing(true);

        mainPresenter.doMovie();
        // show data in recyclerview
        swipeRefreshLayout.setOnRefreshListener(() -> {

            mainAdapter.notifyDataSetChanged();
            mainPresenter.doMovie();

        });
    }

    void initView(){
        mainPresenter = new MainPresenter(context, this);
        mainAdapter = new MainAdapter(context);
        recyclerMovie.setLayoutManager(new LinearLayoutManager(context));
        recyclerMovie.setHasFixedSize(true);
        recyclerMovie.setItemAnimator(new DefaultItemAnimator());
        recyclerMovie.setAdapter(mainAdapter);
    }

    @Override
    public void getMovie(Cursor cursor) {
        mainAdapter.setMovieList(cursor);
        recyclerMovie.setAdapter(mainAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
