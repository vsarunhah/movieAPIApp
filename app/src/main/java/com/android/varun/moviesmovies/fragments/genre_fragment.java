package com.android.varun.moviesmovies.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.varun.moviesmovies.R;
import com.android.varun.moviesmovies.fetchGenre;
import com.android.varun.moviesmovies.fetchResults;

public class genre_fragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    fetchGenre fr;


    public genre_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.genreRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.movieProgressGenre);
        fr = new fetchGenre(getContext(), recyclerView, progressBar);
        fr.execute("genre", " ");

        return view;

    }
}
