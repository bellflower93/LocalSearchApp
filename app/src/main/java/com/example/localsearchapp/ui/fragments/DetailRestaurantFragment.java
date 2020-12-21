package com.example.localsearchapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.localsearchapp.R;
import com.example.localsearchapp.databinding.FragmentDetailRestaurantBinding;
import com.example.localsearchapp.databinding.FragmentListRestaurantBinding;
import com.example.localsearchapp.domain.Restaurant;

public class DetailRestaurantFragment extends Fragment {

    public static String FRAGMENT_TAG = "DetailRestaurantFragment";
    FragmentDetailRestaurantBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailRestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailRestaurantBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        Restaurant restaurant = (Restaurant) bundle.getSerializable("restaurantData");

        setUpViews(restaurant);
    }

    public void setUpViews(Restaurant restaurant) {
        binding.restaurantName.setText(restaurant.getTitle());
        binding.restaurantAddress.setText(restaurant.getSubtitle());

        //binding.restaurantTags.setText(restaurant.getTitle());
    }
}