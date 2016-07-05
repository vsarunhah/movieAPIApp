package com.android.varun.moviesmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.IconMarginSpan;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.varun.moviesmovies.models.MovieTrailer;
import com.android.varun.moviesmovies.models.movieModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.datatype.Duration;

public class detailLayout extends AppCompatActivity {
    TextView title;
    TextView rating;
    ImageView backdrop;
    TextView releaseDate;
    TextView synopsis;
    LinearLayout linear;
    Uri buildUri;
    URL url;
    String JSOnstr;
    ArrayList movieTrailer = new ArrayList();
    Context context;
    FloatingActionButton trailerPlayer;
    RecyclerView rv;
    ProgressBar pb;
    fetchGenre fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layout);
        final movieModel item = (movieModel) getIntent().getExtras().getSerializable("clicked_item");

        title = (TextView) findViewById(R.id.movieTitleDetailed);
        rating = (TextView) findViewById(R.id.ratingDetailed);
        backdrop = (ImageView) findViewById(R.id.movieBackdropDetailed);
        releaseDate = (TextView) findViewById(R.id.releaseDateDetailed);
        synopsis = (TextView) findViewById(R.id.overviewDetailed);
        linear = (LinearLayout) findViewById(R.id.linear);
        trailerPlayer = (FloatingActionButton) findViewById(R.id.playTrailer);
        pb = (ProgressBar) findViewById(R.id.detailPB);
        rv = (RecyclerView) findViewById(R.id.detailRV);
        fg = new fetchGenre(this, rv, pb);
        fg.execute("list", item.getId().toString());


        rating.setText(item.getVote_average().toString());
        synopsis.setText(item.getOverview());
        releaseDate.setText(item.getReleaseDate());
        title.setText(item.getTitle());


        Picasso.with(this).load(item.getPoster_path()).into(backdrop);
        trailerFetch(item.getId());

        final ArrayList<MovieTrailer> trailers = trailerFetch(item.getId());

        trailerPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="
                + trailers.get(0).getTrailerUrl()));
                Log.d("fetchresults", "clicked");
                startActivity(i);
            }
        });
    }

    //Start with trailer stuff
    public ArrayList<MovieTrailer> trailerFetch(final int id) {

        AsyncTask<String, Void, ArrayList<MovieTrailer>> trailerFetch
                = new AsyncTask<String, Void, ArrayList<MovieTrailer>>() {
            @Override
            protected ArrayList<MovieTrailer> doInBackground(String... params) {

                HttpURLConnection client = null;
                JSOnstr = null;
                BufferedReader reader = null;

                final String BASE_URL = "https://api.themoviedb.org/3/";
                final String MOVIE_SEGMENT = "movie";
                final String API_KEY = "cbb709a54021ed57c1f3c5ffc01e9210";
                final String API_KEY_PARAM = "api_key";
                final String VIDEO_SEGMENT = "videos";

                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(MOVIE_SEGMENT)
                        .appendPath(String.valueOf(id))
                        .appendPath(VIDEO_SEGMENT)
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .build();



                try {
                    url = new URL(buildUri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Log.d("fetchResults", url.toString());

                try {
                    client = (HttpURLConnection) url.openConnection();
                    client.setRequestMethod("GET");
                    client.connect();
                    InputStream inputStream = client.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder builder = new StringBuilder();

                    String line;

                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    JSOnstr = builder.toString();
                    JSONObject jsonData = new JSONObject(JSOnstr);
                    JSONArray resultArray = jsonData.getJSONArray("results");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String status;

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject movieItem = resultArray.getJSONObject(i);
                        String key = movieItem.getString("key");
                        String name = movieItem.getString("name");
                        movieTrailer.add(new MovieTrailer(key, name));


                    }
                    Log.d("fetchResults", "Trailer fetched");
                    return movieTrailer;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        trailerFetch.execute();
        return movieTrailer;
    }

    private ArrayList<MovieTrailer> getJSON(String data) throws JSONException, ParseException, java.text.ParseException {
        final String RESULT_ARRAY = "results";
        final String MOVIE_TITLE = "title";
        final String OVERVIEW = "overview";
        final String STATUS = "status";
        final String RATING = "vote_average";
        final String POSTER_URL = "poster_path";
        final String BACKDROP_URL = "backdrop_path";
        final String ID = "id";
        final String RELEASE_DATE = "release_date";



        return movieTrailer;
    }




}


