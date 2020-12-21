package com.example.localsearchapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.localsearchapp.domain.Restaurant;
import com.example.localsearchapp.repository.RestaurantRepository;

import java.util.ArrayList;

public class RestaurantViewModel extends AndroidViewModel {

    RestaurantRepository restaurantRepository;
    LiveData<ArrayList<Restaurant>> liveDataListOfRestaurant;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        restaurantRepository = new RestaurantRepository();
        liveDataListOfRestaurant = restaurantRepository.getListOfRestaurants();
    }

    public void getRestaurant() {
        restaurantRepository.getRestaurants();
    }

    public LiveData<ArrayList<Restaurant>> getLiveDataListOfRestaurant() {
        return liveDataListOfRestaurant;
    }

}
