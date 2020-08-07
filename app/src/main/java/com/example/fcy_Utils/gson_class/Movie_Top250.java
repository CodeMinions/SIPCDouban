package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie_Top250 {
    @SerializedName("subjects")
    public List<Subject> subjects;



    public static class Subject {
        @SerializedName("rating")
        public Rating rating;
        @SerializedName("genres")
        public List<String> genres;
        @SerializedName("title")
        public String title;
        @SerializedName("original_title")
        public String original_title;
        @SerializedName("id")
        public String doubanId;
        @SerializedName("images")
        public My_Images images;
    }
}
