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
public class upcoming extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    fetchResults fr;



    public upcoming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.upcomingRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.upcoming_progress);
        fr = new fetchResults(getContext(), recyclerView, progressBar);
        fr.execute("upcoming", " ");

        // Inflate the layout for this fragment
        return view;

    }

}
