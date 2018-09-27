package fikrims.io.myfavorite.feature.detail_movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fikrims.io.myfavorite.R;
import fikrims.io.myfavorite.data.provider.FavoriteModel;
import fikrims.io.myfavorite.utils.Constant;
import fikrims.io.myfavorite.utils.DateFormator;

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

    private boolean status;
    private FavoriteModel movie = new FavoriteModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar(toolbar);
/*        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        ButterKnife.bind(this);


        movie = getIntent().getParcelableExtra(Constant.Key.MOVIE_DETAIL);
        updateUI(movie);
    }

    void updateUI(FavoriteModel movie){

        Picasso.with(this)
                .load(Constant.Key.BASE_POSTER_URL+movie.getPosterPath())
                .placeholder(R.drawable.ic_refresh_white)
                .error(R.drawable.ic_refresh_white)
                .into(poster);

        Picasso.with(this)
                .load(Constant.Key.BASE_BACKDROP_URL+movie.getBackdropPath())
                .placeholder(R.drawable.ic_refresh_white)
                .error(R.drawable.ic_refresh_white)
                .into(backDrop);
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText("Release Date : "+ DateFormator.getDateDay(movie.getReleaseDate()));
        tvRating.setText("Rating : "+movie.getVoteAverage());
    }

    @OnClick(R.id.img_back)
    void onClicked(){
        finish();
    }
}
