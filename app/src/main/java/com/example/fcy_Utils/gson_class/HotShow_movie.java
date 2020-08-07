package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public class HotShow_movie extends LitePalSupport {
    @SerializedName("doubanId")
    public String douBanId;
    @SerializedName("img")
    public String imgUrl;
    @SerializedName("name")
    public String movieName;

}
