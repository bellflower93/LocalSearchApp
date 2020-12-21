package com.example.localsearchapp.utility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


public class UIUtil {

    public static void addFragment(AppCompatActivity activity, int containerViewId, Fragment fragment, String fragmentTag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .commit();
    }

    public static void replaceFragment(FragmentActivity activity, int containerViewId, Fragment fragment, String fragmentTag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .commit();
    }
}
