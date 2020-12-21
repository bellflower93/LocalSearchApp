package com.example.localsearchapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.localsearchapp.configuration.Configuration;
import com.example.localsearchapp.domain.Restaurant;
import com.example.localsearchapp.domain.RestaurantContainer;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestaurantRepository {

    MutableLiveData<ArrayList<Restaurant>> listOfRestaurants = new MutableLiveData<>();

    public void getRestaurants() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Configuration.API_URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listOfRestaurants.postValue(null);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Gson gson = new Gson();
                RestaurantContainer restaurantContainer = gson.fromJson(response.body().string(), RestaurantContainer.class);
                listOfRestaurants.postValue(restaurantContainer.getListOfRestaurant());
            }
        });
    }

    public LiveData<ArrayList<Restaurant>> getListOfRestaurants() {
        return listOfRestaurants;
    }

}
