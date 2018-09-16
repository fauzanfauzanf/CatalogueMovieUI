package fikrims.io.moviecatalogueui.feature.main.favorite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.database.MovieHelper;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.feature.detail_movie.DetailActivity;
import fikrims.io.moviecatalogueui.feature.main.MainActivity;
import fikrims.io.moviecatalogueui.utils.Constant;

@SuppressLint("InflateParams")
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private List<MovieResult> listMovie;

    FavoriteAdapter(Context context){
        this.listMovie = new ArrayList<>();
        this.context = context;
    }

    public void setMovieResult(List<MovieResult> listMovie){
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, null);
        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
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
        @BindView(R.id.btn_remove)
        Button btnRemove;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieResult movieResult){
            btnRemove.setVisibility(View.VISIBLE);
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

            btnRemove.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                MovieHelper movieHelper = new MovieHelper(context);
                // Add the buttons
                builder.setTitle(R.string.app_name);
                builder.setMessage("Anda ingin menghapus film "+movieResult.getTitle()+" dari favorite ?");
                builder.setPositiveButton(R.string.yes, (dialog, id) -> {
                    // User clicked OK button
                    movieHelper.open();
                    movieHelper.delete(movieResult.getId());
                    movieHelper.close();
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity)context).finish();
                });
                builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                    dialog.dismiss();
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            });
        }
    }
}
