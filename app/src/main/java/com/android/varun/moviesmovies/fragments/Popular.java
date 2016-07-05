package com.android.varun.moviesmovies.fragments;


import android.os.Bundle;
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
public class Popular extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    fetchResults fr;


    public Popular() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.popularRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.popular_progress);
        fr = new fetchResults(getContext(), recyclerView, progressBar);
        fr.execute("top", " ");

        // Inflate the layout for this fragment
        return view;
    }

}
