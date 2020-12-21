package com.example.localsearchapp.ui.fragments;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.localsearchapp.R;
import com.example.localsearchapp.adapters.ListRestaurantAdapter;
import com.example.localsearchapp.databinding.FragmentListRestaurantBinding;
import com.example.localsearchapp.domain.Restaurant;
import com.example.localsearchapp.interfaces.OnItemClickListener;
import com.example.localsearchapp.utility.UIUtil;
import com.example.localsearchapp.viewmodel.RestaurantViewModel;

import java.util.ArrayList;

public class ListRestaurantFragment extends Fragment implements OnItemClickListener {

    public static String FRAGMENT_TAG = "ListRestaurantFragment";

    private FragmentListRestaurantBinding binding;
    private RestaurantViewModel restaurantViewModel;
    private ListRestaurantAdapter adapter;
    RecyclerView.OnScrollListener onScrollListener;
    int pageSize = 10;
    boolean isLoading = false;

    public ListRestaurantFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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
                isLoading = false;
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
        adapter.setOnItemClickListener(this);
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItems = layoutManager.getChildCount();
                int totalItems = 30;
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading &&
                        (visibleItems + firstVisiblePosition) >= layoutManager.getItemCount()
                            && layoutManager.getItemCount() < totalItems) {
                        isLoading = true;
                        restaurantViewModel.getRestaurant();
                }

            }
        };
        binding.listOfRestaurants.addOnScrollListener(onScrollListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemClick(Restaurant restaurant) {

        DetailRestaurantFragment detailRestaurantFragment = new DetailRestaurantFragment();
        Bundle data = new Bundle();
        data.putSerializable("restaurantData", restaurant);
        detailRestaurantFragment.setArguments(data);

        UIUtil.replaceFragment(getActivity(), R.id.fragment_container, detailRestaurantFragment, DetailRestaurantFragment.FRAGMENT_TAG);
    }
}