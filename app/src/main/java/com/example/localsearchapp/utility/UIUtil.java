package com.example.localsearchapp.utility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class UIUtil {

    public static void addFragment(AppCompatActivity activity, int containerViewId, Fragment fragment, String fragmentTag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .commit();
    }
}
