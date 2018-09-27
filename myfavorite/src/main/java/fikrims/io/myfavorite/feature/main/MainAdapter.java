package fikrims.io.myfavorite.feature.main;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import fikrims.io.myfavorite.R;
import fikrims.io.myfavorite.data.provider.FavoriteModel;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private Cursor list;

    MainAdapter(Context context){
        this.context = context;
    }

    void setMovieList(Cursor list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, null);
        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private FavoriteModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavoriteModel(list);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_movie)
        ImageView imageMovie;

        @BindView(R.id.text_title)
        TextView textTitle;

        @BindView(R.id.text_overview)
        TextView textOverview;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(FavoriteModel item){
            textTitle.setText(item.getTitle());
            textOverview.setText(item.getOverview());

            Glide.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w154/" + item.getPosterPath())
                    .into(imageMovie);

            itemView.setOnClickListener(view -> {
                /*Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                itemView.getContext().startActivity(intent);*/
            });
        }
    }
}
