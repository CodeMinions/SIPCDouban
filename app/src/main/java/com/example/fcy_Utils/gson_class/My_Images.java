package com.example.fcy_Utils.gson_class;


import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public  class My_Images extends LitePalSupport {
        @SerializedName("small")
        public String small_url;

    }
