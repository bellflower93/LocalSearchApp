package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

public class Icons {

    @SerializedName("result_list")
    MainPicture picture;

    @SerializedName("list_badge")
    String iconURL;

    public MainPicture getPicture() {
        return picture;
    }

    public String getIconURL() {
        return iconURL;
    }

}
