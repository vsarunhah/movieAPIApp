package com.android.varun.moviesmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.varun.moviesmovies.models.genreModel;
import com.android.varun.moviesmovies.models.movieModel;

import org.w3c.dom.Text;

public class detailGenreLayout extends AppCompatActivity {
    RecyclerView movies;
    ProgressBar pb;
    fetchResults fr;
    genreModel item;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_genre_layout);

        movies = (RecyclerView) findViewById(R.id.genreDetailRecycler);
        pb = (ProgressBar) findViewById(R.id.genreDetailProgess);
        fr = new fetchResults(this, movies, pb);
        item = (genreModel) getIntent().getExtras().getSerializable("clicked_item");
        id = item.getId();

        fr.execute("genre", String.valueOf(id) );

    }
}
