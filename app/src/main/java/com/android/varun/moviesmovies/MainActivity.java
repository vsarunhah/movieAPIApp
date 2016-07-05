package com.android.varun.moviesmovies;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TableLayout;

import com.android.varun.moviesmovies.fragments.Popular;
import com.android.varun.moviesmovies.fragments.discover_fragment;
import com.android.varun.moviesmovies.fragments.genre_fragment;
import com.android.varun.moviesmovies.fragments.upcoming;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TabLayout tabs;
    ViewPager viewPager;
    fetchResults FetchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
setSupportActionBar(toolbar);
        toolbar.setTitle("Movies? Movies!");

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new discover_fragment(), "Find");
        adapter.addFragment(new genre_fragment(), "Genres");
        adapter.addFragment(new upcoming(), "Coming");
        adapter.addFragment(new Popular(), "Top");
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);

        //Asynctask stuff


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu , menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
