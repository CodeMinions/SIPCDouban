package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public  class Details extends LitePalSupport {
            @SerializedName("1")
            public double one;
            @SerializedName("2")
            public double two;
            @SerializedName("3")
            public double three;
            @SerializedName("4")
            public double four;
            @SerializedName("5")
            public double five;
}