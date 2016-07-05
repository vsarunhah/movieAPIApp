package com.android.varun.moviesmovies;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.varun.moviesmovies.models.movieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList<movieModel> mMovies;
    private Context c;

    public MyListAdapter(ArrayList<movieModel> movies, Context context) {
        mMovies = movies;
        c = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_row,
                parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        movieModel movie = mMovies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieStatus.setText(movie.getStatus());

        Picasso.with(c).load(movie.getPoster_path())
                .resize(600, 900)
                .into(holder.moviePoster);

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView movieStatus;
        ImageView moviePoster;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.movieTitle = (TextView) itemView.findViewById(R.id.movie_Title);
            this.moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            this.movieStatus = (TextView) itemView.findViewById(R.id.status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieModel clickedItem = mMovies.get(getAdapterPosition());

                    Intent i;
                    i = new Intent(c.getApplicationContext(), detailLayout.class);
                    ActivityOptionsCompat ao = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) c,
                            moviePoster, c.getResources().getString(R.string.shareTag));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("clicked_item", clickedItem);
                    i.putExtras(bundle);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        c.startActivity(i, ao.toBundle());
                    } else
                        c.startActivity(i);
                    Log.d("Adapter ", getAdapterPosition() + " ");

                }
            });
        }

    }

}
