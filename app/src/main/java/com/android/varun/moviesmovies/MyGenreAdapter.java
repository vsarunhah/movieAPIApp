package com.android.varun.moviesmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.varun.moviesmovies.models.genreModel;
import com.android.varun.moviesmovies.models.movieModel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


public class MyGenreAdapter extends RecyclerView.Adapter<MyGenreAdapter.ViewHolder> {
    private ArrayList<genreModel> mGenres;
    private Context c;

    public MyGenreAdapter(ArrayList<genreModel> genres, Context context) {
        mGenres = genres;
        c = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item_row,
                parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        genreModel genre = mGenres.get(position);
        holder.genreTitle.setText(genre.getName());


    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView genreTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.genreTitle = (TextView) itemView.findViewById(R.id.genreNameTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genreModel clickedItem = mGenres.get(getAdapterPosition());

                    Intent i;
                    i = new Intent(c.getApplicationContext(), detailGenreLayout.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("clicked_item",  clickedItem);
                    i.putExtras(bundle);
                    c.startActivity(i);
                    Log.d("Adapter ", getAdapterPosition() + " ");

                }
            });
        }

    }
}
