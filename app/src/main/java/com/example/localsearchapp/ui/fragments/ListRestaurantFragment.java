package com.example.localsearchapp.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.localsearchapp.R;
import com.example.localsearchapp.adapters.ListRestaurantAdapter;
import com.example.localsearchapp.databinding.FragmentListRestaurantBinding;
import com.example.localsearchapp.domain.Restaurant;
import com.example.localsearchapp.viewmodel.RestaurantViewModel;

import java.util.ArrayList;

public class ListRestaurantFragment extends Fragment {

    public static String FRAGMENT_TAG = "ListRestaurantFragment";

    private FragmentListRestaurantBinding binding;
    private RestaurantViewModel restaurantViewModel;
    private ListRestaurantAdapter adapter;

    public ListRestaurantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListRestaurantBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView();

        restaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        restaurantViewModel.init();
        restaurantViewModel.getRestaurant();
        restaurantViewModel.getLiveDataListOfRestaurant().observe(getViewLifecycleOwner(), new Observer<ArrayList<Restaurant>>() {
            @Override
            public void onChanged(ArrayList<Restaurant> restaurants) {
                if (restaurants != null && !restaurants.isEmpty()) {
                    adapter.setListOfRestaurants(restaurants);
                } else {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.empty_data), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setUpRecyclerView() {
        adapter = new ListRestaurantAdapter(getContext());
        binding.listOfRestaurants.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.listOfRestaurants.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.listOfRestaurants.getContext(),
                layoutManager.getOrientation());
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.divider_restaurant_list, null);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        binding.listOfRestaurants.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}