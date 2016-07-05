package com.android.varun.moviesmovies;

import android.content.Context;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Process;
import android.speech.tts.Voice;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.varun.moviesmovies.fragments.discover_fragment;
import com.android.varun.moviesmovies.models.movieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class fetchResults extends AsyncTask<String, Void, ArrayList<movieModel>>
{
    Uri buildUri;
    URL url;
    String JSOnstr;
    ArrayList movieItems = new ArrayList();
    Context context;
    RecyclerView recyclerView;
    ProgressBar progressBar;


    public fetchResults(Context context, RecyclerView rv, ProgressBar bar){
        super();
        this.context = context;
        recyclerView = rv;
        progressBar = bar;
    }

    @Override
    protected ArrayList<movieModel> doInBackground(String... params){
        if (params == null){
            return null;
        }
        String whichTab = params[0];
        String searchQuery = params[1];

        HttpURLConnection client = null;
        JSOnstr = null;
        BufferedReader reader = null;

        final String BASE_URL = "https://api.themoviedb.org/3/";
        final String MOVIE_SEGMENT = "movie";
        final String MOVIES_SEGMENT = "movies";
        final String API_KEY =  "cbb709a54021ed57c1f3c5ffc01e9210";
        final String API_KEY_PARAM = "api_key";
        final String DISCOVER = "discover";
        final String SEARCH = "search";
        final String QUERY = "query";
        final String GENRE = "genre";
        final String LIST = "list";
        final String UPCOMING = "upcoming";

        switch (whichTab){
            case "discover":
                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(DISCOVER)
                        .appendPath(MOVIE_SEGMENT)
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .build();
                break;
            case "search":
                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(SEARCH)
                        .appendPath(MOVIE_SEGMENT)
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .appendQueryParameter(QUERY, searchQuery)
                        .build();
                break;
            case "genre":
                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(GENRE)
                        .appendPath(searchQuery)
                        .appendPath(MOVIES_SEGMENT)
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .build();
                break;
            case "upcoming":
                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(MOVIE_SEGMENT)
                        .appendPath(UPCOMING)
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .build();
                break;

            case "top":
                buildUri = Uri.parse(BASE_URL).buildUpon()
                        .appendPath(MOVIE_SEGMENT)
                        .appendPath("top_rated")
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .build();
                break;



        }



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

            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            JSOnstr = builder.toString();
            Log.d("fetchResults", JSOnstr);
            return getJSON(JSOnstr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ArrayList<movieModel> getJSON(String data) throws JSONException, ParseException, java.text.ParseException {
        final String RESULT_ARRAY = "results";
        final String MOVIE_TITLE = "title";
        final String OVERVIEW = "overview";
        final String STATUS = "status";
        final String RATING = "vote_average";
        final String POSTER_URL = "poster_path";
        final String BACKDROP_URL = "backdrop_path";
        final String ID = "id";
        final String RELEASE_DATE = "release_date";

        JSONObject jsonData = new JSONObject(data);
        JSONArray resultArray = jsonData.getJSONArray(RESULT_ARRAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String status;

        for (int i = 0; i<resultArray.length(); i++){
            JSONObject movieItem = resultArray.getJSONObject(i);
            String movieTitle = movieItem.getString(MOVIE_TITLE);
            String releaseDate = movieItem.getString(RELEASE_DATE);
            String overview = movieItem.getString(OVERVIEW);
            double rating = movieItem.getDouble(RATING);
            String posterURL = movieItem.getString(POSTER_URL);
            String backdropURL = movieItem.getString(BACKDROP_URL);
            int id = movieItem.getInt(ID);
            Date releasedOn = sdf.parse(releaseDate);
            if ((releasedOn.compareTo(date)) <=0){
                status = "Released";
            } else{
                status = "Upcoming";
            }
            movieItems.add(new movieModel(movieTitle, overview,
                    "https://image.tmdb.org/t/p/original" + backdropURL,
                    status,
                    "https://image.tmdb.org/t/p/original"+ posterURL,
                    releaseDate, id, (float)rating ));



        }
        return movieItems;
    }

    @Override
    protected void onPostExecute(ArrayList<movieModel> movieModels) {
        super.onPostExecute(movieModels);
        if (movieModels != null){
            Log.d("fetchResults", movieModels.size() + "");
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            MyListAdapter myAdapter = new MyListAdapter(movieModels, context);
             recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
        }


    }
}
