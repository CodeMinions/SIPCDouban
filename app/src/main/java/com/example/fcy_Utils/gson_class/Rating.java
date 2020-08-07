package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public  class Rating extends LitePalSupport {
        @SerializedName("max")
        public String max;
        @SerializedName("average")
        public String average;
        @SerializedName("details")
        public Details details;



    }
