package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public  class Avatars extends LitePalSupport {
        @SerializedName("small")
        public String img_url;
    }
