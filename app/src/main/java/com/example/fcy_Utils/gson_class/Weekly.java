package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weekly {
    @SerializedName("subjects")
    public List<PSubject> subjects;

    public static class PSubject{
        @SerializedName("subject")
        public Movie_Top250.Subject subject;
    }
}
