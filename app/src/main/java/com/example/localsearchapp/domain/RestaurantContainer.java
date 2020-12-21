package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantContainer {

    @SerializedName("results")
    ArrayList<Restaurant> listOfRestaurant;

    public ArrayList<Restaurant> getListOfRestaurant() {
        return listOfRestaurant;
    }

}
