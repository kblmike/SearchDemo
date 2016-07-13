package com.example.kin.volley.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kin on 6/25/2016.
 */
public class Keyword implements Serializable {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
