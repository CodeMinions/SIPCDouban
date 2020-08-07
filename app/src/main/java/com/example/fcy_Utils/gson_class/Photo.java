package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public  class Photo extends LitePalSupport {
        @SerializedName("thumb")
        public String imgUrl;
    }
