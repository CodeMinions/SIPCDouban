package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public class Cast extends LitePalSupport {
    @SerializedName("avatars")
    public Avatars avatars;
    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
}