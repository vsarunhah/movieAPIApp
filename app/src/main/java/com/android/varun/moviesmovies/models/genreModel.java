package com.android.varun.moviesmovies.models;

import java.io.Serializable;

public class genreModel implements Serializable
{
  String name;
    Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public genreModel(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
