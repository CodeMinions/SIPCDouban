package com.example.fcy_Utils.LitePal_Class;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

import java.util.Objects;

public class Movie_detail extends LitePalSupport {
    @SerializedName("rating")
    private Rating rating;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("images")
    private My_Images imgUrl;
    @SerializedName("year")
    private String year;
    @SerializedName("id")
    private String doubanId;
    @SerializedName("title")
    private String title_ch;
    @SerializedName("pubdate")
    private String year_update;
    @SerializedName("tags")
    private String[] tags;
    @SerializedName("durations")
    private String[] time;
    @SerializedName("genres")
    private String[] categories;
    @SerializedName("trailer_urls")
    private String[] trailer_urls;
    @SerializedName("casts")
    private Cast[] casts;
    @SerializedName("mainland_pubdate")
    private String mainland_time;
    @SerializedName("photos")
    private Photo[] photos;
    @SerializedName("summary")
    private String briefIntroduction;
    @SerializedName("subtype")
    private String type;
    @SerializedName("directors")
    private Director[] directors;


    private static class Director {
        @SerializedName("small")
        private Avatars avatars;
        @SerializedName("name")
        private String name;
    }

    private static class Photo {
        @SerializedName("thumb")
        private String imgUrl;
    }


    private static class Rating {
        @SerializedName("max")
        private String max;
        @SerializedName("average")
        private String average;
        @SerializedName("details")
        private Details details;

        private static class Details {
            @SerializedName("1")
            private String one;
            @SerializedName("2")
            private String two;
            @SerializedName("3")
            private String three;
            @SerializedName("4")
            private String four;
            @SerializedName("5")
            private String five;
        }
    }

    private static class Cast {
        @SerializedName("avatars")
        private Avatars avatars;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;


    }

    private static class Avatars {
        @SerializedName("small")
        private String img_url;
    }

    public class My_Images {
        @SerializedName("small")
        private String small_url;

        public String getSmall_url() {
            return small_url;
        }

        public void setSmall_url(String small_url) {
            this.small_url = small_url;
        }
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public My_Images getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(My_Images imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public String getTitle_ch() {
        return title_ch;
    }

    public void setTitle_ch(String title_ch) {
        this.title_ch = title_ch;
    }

    public String getYear_update() {
        return year_update;
    }

    public void setYear_update(String year_update) {
        this.year_update = year_update;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getTrailer_urls() {
        return trailer_urls;
    }

    public void setTrailer_urls(String[] trailer_urls) {
        this.trailer_urls = trailer_urls;
    }

    public Cast[] getCasts() {
        return casts;
    }

    public void setCasts(Cast[] casts) {
        this.casts = casts;
    }

    public String getMainland_time() {
        return mainland_time;
    }

    public void setMainland_time(String mainland_time) {
        this.mainland_time = mainland_time;
    }

    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Director[] getDirectors() {
        return directors;
    }

    public void setDirectors(Director[] directors) {
        this.directors = directors;
    }

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
}
