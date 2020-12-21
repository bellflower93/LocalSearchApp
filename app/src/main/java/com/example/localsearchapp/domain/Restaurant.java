package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Restaurant {

    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("subtitle")
    String subtitle;

    @SerializedName("categories")
    ArrayList<Category> category;

    @SerializedName("icons")
    Icons pictures;

    @SerializedName("primary_action")
    Action action;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public Icons getPictures() {
        return pictures;
    }

    public Action getAction() {
        return action;
    }

}
