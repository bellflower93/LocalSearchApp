package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }
}
