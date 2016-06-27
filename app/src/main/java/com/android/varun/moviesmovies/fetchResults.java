package com.android.varun.moviesmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;

import com.android.varun.moviesmovies.models.movieModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class fetchResults extends AsyncTask<String, Void, ArrayList<movieModel>>
{
    Uri buildUri;
    URL url;
    String JSOnstr;

    public fetchResults(){
        super();
    }

    @Override
    protected ArrayList<movieModel> doInBackground(String... params){
        if (params == null){
            return null;
        }
        String whichTab = params[0];
        String seatchQuery = params[1];

        HttpURLConnection client = null;
        JSOnstr = null;
        BufferedReader reader = null;

        final String BASE_URL = "https://api.themoviedb.org/3/";
        final String MOVIE_SEGMENT = "movie";
        final String API_KEY =  "cbb709a54021ed57c1f3c5ffc01e9210";
        final String API_KEY_PARAM = "api_key";
        final String DISCOVER = "discover";

        buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(DISCOVER)
                .appendPath(MOVIE_SEGMENT)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
