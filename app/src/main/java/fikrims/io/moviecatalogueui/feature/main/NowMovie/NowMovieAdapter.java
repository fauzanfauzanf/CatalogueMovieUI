package fikrims.io.moviecatalogueui.feature.main.NowMovie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.feature.detail_movie.DetailActivity;
import fikrims.io.moviecatalogueui.utils.Constant;

public class NowMovieAdapter extends RecyclerView.Adapter<NowMovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieResult> listMovie;

    NowMovieAdapter(Context context){
        this.listMovie = new ArrayList<>();
        this.context = context;
    }

    public void setMovieResult(List<MovieResult> listMovie){
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public NowMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, null);
        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NowMovieAdapter.ViewHolder holder, int position) {
        holder.bind(listMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_movie)
        ImageView imageMovie;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_rating)
        TextView textRating;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieResult movieResult){
            Picasso.with(itemView.getContext())
                    .load(Constant.Utils.BASE_POSTER_URL+movieResult.getPosterPath())
                    .placeholder(R.mipmap.play)
                    .error(R.drawable.ic_refresh)
                    .into(imageMovie);
            imageMovie.setPadding(0,0,0,0);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(Constant.Utils.MOVIE_DETAIL, movieResult);
                itemView.getContext().startActivity(intent);
            });

            textTitle.setText(movieResult.getTitle());
            textRating.setText("Rating : "+movieResult.getOverview());
        }
    }
}
