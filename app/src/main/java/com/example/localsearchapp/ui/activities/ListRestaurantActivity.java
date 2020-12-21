package com.example.localsearchapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.localsearchapp.R;
import com.example.localsearchapp.ui.fragments.ListRestaurantFragment;
import com.example.localsearchapp.utility.UIUtil;

public class ListRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);

        UIUtil.addFragment(this, R.id.fragment_container, new ListRestaurantFragment(), ListRestaurantFragment.FRAGMENT_TAG);
    }

}