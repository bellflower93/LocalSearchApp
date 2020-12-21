package com.example.localsearchapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.localsearchapp.databinding.RowRestaurantBinding;
import com.example.localsearchapp.domain.Category;
import com.example.localsearchapp.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class ListRestaurantAdapter extends RecyclerView.Adapter<ListRestaurantAdapter.RestaurantViewHolder> {

    Context context;
    List<Restaurant> listOfRestaurants;
    RowRestaurantBinding binding;

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private RestaurantViewHolder(View itemView) {
            super(itemView);
        }

    }

    public ListRestaurantAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListRestaurantAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        binding = RowRestaurantBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RestaurantViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = listOfRestaurants.get(position);

        binding.restaurantName.setText(restaurant.getTitle());
        binding.restaurantAddress.setText(restaurant.getSubtitle());
        StringBuilder tags = new StringBuilder();
        ArrayList<Category> listCategory = restaurant.getCategory();
        for (int i = 0; i < listCategory.size(); i++) {
            String categoryName = listCategory.get(i).getName();
            if (i == listCategory.size() - 1) {
                tags.append(categoryName);
            } else {
                tags.append(categoryName).append(" · ");
            }
        }
        binding.restaurantTags.setText(tags.toString());
        Glide.with(context)
                .load(restaurant.getAction().getIconURL())
                .apply(new RequestOptions().fitCenter())
                .into(binding.callRestaurantImg);
        Glide.with(context)
                .load(restaurant.getPictures().getPicture().getPictureURL())
                .into(binding.imageOfRestaurant);
        Glide.with(context)
                .load(restaurant.getPictures().getIconURL())
                .into(binding.redIcon);
        binding.callRestaturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(restaurant.getAction().getUri()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listOfRestaurants != null) {
            return listOfRestaurants.size();
        } else {
            return 0;
        }
    }

    public void setListOfRestaurants(List<Restaurant> restaurantList) {
        listOfRestaurants = restaurantList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}