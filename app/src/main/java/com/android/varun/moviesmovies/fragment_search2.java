package com.android.varun.moviesmovies;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

public class fragment_search2 extends AppCompatActivity {
    fetchResults fr;
    RecyclerView rv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search2);
        rv = (RecyclerView) findViewById(R.id.searchRecylerView);
        pb = (ProgressBar) findViewById(R.id.searchProgressBar);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.d("se", "searching");
            String query = intent.getStringExtra(SearchManager.QUERY);
            fr = new fetchResults(this, rv, pb);
            fr.execute("search", query);
        }
    }
}
