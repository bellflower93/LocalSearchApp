package com.example.localsearchapp.domain;

import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("icon_url")
    String iconURL;

    @SerializedName("uri")
    String uri;

    public String getIconURL() {
        return iconURL;
    }

    public String getUri() {
        return uri;
    }

}
