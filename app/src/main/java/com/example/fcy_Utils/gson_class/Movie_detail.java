package com.example.fcy_Utils.gson_class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;


import java.util.List;
import java.util.Objects;

public class Movie_detail extends LitePalSupport {

    @SerializedName("rating")
    public Rating rating;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("images")
    public My_Images imgUrl;
    @SerializedName("year")
    public String year;
    @SerializedName("id")
    public String doubanId;
    @SerializedName("title")
    public String title_ch;
    @SerializedName("pubdate")
    public String year_update;
    @SerializedName("tags")
    public List<String> tags;
    @SerializedName("durations")
    public List<String> time;
    @SerializedName("genres")
    public List<String> categories;
    @SerializedName("trailer_urls")
    public List<String> trailer_urls;
    @SerializedName("casts")
    public List<Cast> casts;
    @SerializedName("mainland_pubdate")
    public String mainland_time;
    @SerializedName("photos")
    public List<Photo> photos;
    @SerializedName("summary")
    public String briefIntroduction;
    @SerializedName("subtype")
    public String type;
    @SerializedName("directors")
    public List<Director> directors;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie_detail that = (Movie_detail) o;
        return Objects.equals(doubanId, that.doubanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doubanId);
    }

    @Override
    public String toString() {
        return "Movie_detail{" +
                "rating=" + rating +
                ", original_title='" + original_title + '\'' +
                ", imgUrl=" + imgUrl +
                ", year='" + year + '\'' +
                ", doubanId='" + doubanId + '\'' +
                ", title_ch='" + title_ch + '\'' +
                ", year_update='" + year_update + '\'' +
                ", tags=" + tags +
                ", time=" + time +
                ", categories=" + categories +
                ", trailer_urls=" + trailer_urls +
                ", casts=" + casts +
                ", mainland_time='" + mainland_time + '\'' +
                ", photos=" + photos +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", type='" + type + '\'' +
                ", directors=" + directors +
                '}';
    }


}
