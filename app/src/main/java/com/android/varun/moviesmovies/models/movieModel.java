package com.android.varun.moviesmovies.models;

import java.io.Serializable;

public class movieModel implements Serializable
{
    String title, overview, backdrop_path, status, poster_path, releaseDate;
    Integer id;
    Float vote_average;

    public movieModel(String title, String overview, String backdrop_path, String status, String poster_path, String releaseDate, Integer id, Float vote_average) {
        this.title = title;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.status = status;
        this.poster_path = poster_path;
        this.releaseDate = releaseDate;
        this.id = id;
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }
}
