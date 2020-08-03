package com.example.fcy_Utils.LitePal_Class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public class HotShow_movie extends LitePalSupport {
    @SerializedName("doubanId")
    private String douBanId;
    @SerializedName("img")
    private String imgUrl;
    @SerializedName("name")
    private String movieName;

    public String getDouBanId() {
        return douBanId;
    }

    public void setDouBanId(String douBanId) {
        this.douBanId = douBanId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
