package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

public class MainPicture {

    @SerializedName("src")
    String pictureURL;

    public String getPictureURL() {
        return pictureURL;
    }

}
