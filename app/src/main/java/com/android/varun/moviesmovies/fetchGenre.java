package com.android.varun.moviesmovies;

import android.content.Context;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.varun.moviesmovies.models.genreModel;
import com.android.varun.moviesmovies.models.movieModel;

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

public class fetchGenre extends AsyncTask<String,Void, ArrayList<genreModel>>
{

        Uri buildUri;
        URL url;
        String JSOnstr;
        ArrayList genreItems = new ArrayList();
        Context context;
        RecyclerView recyclerView;
        ProgressBar progressBar;
    String tab;



        public fetchGenre(Context context, RecyclerView rv, ProgressBar bar){
            super();
            this.context = context;
            recyclerView = rv;
            progressBar = bar;
        }

        @Override
        protected ArrayList<genreModel> doInBackground(String... params){
            if (params == null){
                return null;
            }
            String whichTab = params[0];
            String movieID = params[1];
            if (movieID == null){
                movieID = " ";
            }

            HttpURLConnection client = null;
            JSOnstr = null;
            BufferedReader reader = null;

            final String BASE_URL = "https://api.themoviedb.org/3/";
            final String MOVIE_SEGMENT = "movie";
            final String API_KEY =  "cbb709a54021ed57c1f3c5ffc01e9210";
            final String API_KEY_PARAM = "api_key";
            final String GENRE = "genre";
            final String LIST = "list";
            final String LISTS = "lists";
            if (whichTab == "list"){
                tab = "list";
            }

            switch (whichTab){
                case "genre":
                    buildUri = Uri.parse(BASE_URL).buildUpon()
                            .appendPath(GENRE)
                            .appendPath(MOVIE_SEGMENT)
                            .appendPath(LIST)
                            .appendQueryParameter(API_KEY_PARAM, API_KEY)
                            .build();
                    break;
                case "list":
                    buildUri = Uri.parse(BASE_URL).buildUpon()
                            .appendPath(MOVIE_SEGMENT)
                            .appendPath(movieID)
                            .appendPath(LISTS)
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
            return genreItems;
        }


        private ArrayList<genreModel> getJSON(String data) throws JSONException, ParseException, java.text.ParseException {
            final String RESULT_ARRAY = "genres";
            final String NAME = "name";
            final String ID = "id";
            final String RESULTS_LISTS = "results";

            JSONObject jsonData = new JSONObject(data);
            JSONArray resultArray;
            if (tab == "list"){
                resultArray = jsonData.getJSONArray(RESULTS_LISTS);
            }else {
                resultArray = jsonData.getJSONArray(RESULT_ARRAY);
            }



            for (int i = 0; i<resultArray.length(); i++){
                JSONObject movieItem = resultArray.getJSONObject(i);
                int id = movieItem.getInt(ID);
                String name = movieItem.getString(NAME);
                genreItems.add(new genreModel(name,id));


            }
            return genreItems;
        }

    @Override
        protected void onPostExecute(ArrayList<genreModel> genreModels) {
            super.onPostExecute(genreModels);
            if (genreModels != null){
                Log.d("fetchResults", genreModels.size() + "");
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                MyGenreAdapter myAdapter = new MyGenreAdapter(genreModels, context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myAdapter);
            }


        }
    }



