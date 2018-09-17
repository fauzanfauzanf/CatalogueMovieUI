package fikrims.io.moviecatalogueui.feature.detail_movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.database.MovieHelper;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.utils.Constant;
import fikrims.io.moviecatalogueui.utils.DateFormator;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_overview_tv)
    TextView tvOverview;
    @BindView(R.id.image_detail)
    ImageView backDrop;
    @BindView(R.id.img_back)
    ImageView imageBack;
    @BindView(R.id.item_date_detail)
    TextView tvDate;
    @BindView(R.id.item_title_detail)
    TextView tvTitle;
    @BindView(R.id.movie_poster_detail)
    ImageView poster;
    @BindView(R.id.item_rating_detial)
    TextView tvRating;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;

    private MovieHelper movieHelper;
    private MovieResult movie;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        ButterKnife.bind(this);
        movieHelper = new MovieHelper(this);

        movie = getIntent().getParcelableExtra(Constant.Utils.MOVIE_DETAIL);
        updateUI(movie);
    }

    void updateUI(MovieResult movie){
//        getSupportActionBar().setTitle(movie.getTitle());
        movieHelper.open();
        status = movieHelper.getDataStatus(movie.getId());
        if (status) toggleButton.setChecked(true);
        else toggleButton.setChecked(false);
        movieHelper.close();

        int status = Hawk.get(Constant.Key.ACTIVITY_STATUS);

        Picasso.with(this)
                .load(Constant.Utils.BASE_POSTER_URL+movie.getPosterPath())
                .placeholder(R.mipmap.play)
                .error(R.drawable.ic_refresh)
                .into(poster);

        Picasso.with(this)
                .load(Constant.Utils.BASE_BACKDROP_URL+movie.getBackdropPath())
                .placeholder(R.mipmap.play)
                .error(R.drawable.ic_refresh)
                .into(backDrop);
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText("Release Date : "+ DateFormator.getDateDay(movie.getReleaseDate()));
        tvRating.setText("Rating : "+movie.getVoteAverage().toString());
    }

    @OnClick({R.id.img_back})
    void onClicked(View view){
        int id = view.getId();
        switch (id){
            case R.id.img_back:
                finish();
                break;
        }
    }

    @OnCheckedChanged({R.id.toggleButton})
    void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
//        int status = Hawk.get(Constant.Key.ACTIVITY_STATUS);
        int id = buttonView.getId();
        movie = getIntent().getParcelableExtra(Constant.Utils.MOVIE_DETAIL);
        movieHelper.open();
        switch (id){
            case R.id.toggleButton:
                if (isChecked){
                    if (!status) Toast.makeText(DetailActivity.this,"Add to Favorite", Toast.LENGTH_LONG).show();
                    movieHelper.insert(movie, 1);
                    movieHelper.close();
                } else {
                    Toast.makeText(DetailActivity.this,"Remove from Favorite", Toast.LENGTH_LONG).show();
                    movieHelper.delete(movie.getId());
                    movieHelper.close();
                }
                break;
        }
    }
}
