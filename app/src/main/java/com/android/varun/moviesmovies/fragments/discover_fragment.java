package com.android.varun.moviesmovies.fragments;


import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.varun.moviesmovies.R;
import com.android.varun.moviesmovies.fetchResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class discover_fragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    fetchResults fr;


    public discover_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.discoverRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.movie_progress);
        fr = new fetchResults(getContext(), recyclerView, progressBar);
        fr.execute("discover", " ");




        return view;

    }

}
