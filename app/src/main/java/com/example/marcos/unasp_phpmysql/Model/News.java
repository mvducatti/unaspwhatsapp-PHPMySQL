package com.example.marcos.unasp_phpmysql.Model;

import android.graphics.Bitmap;

/**
 * Created by marcos on 19/03/2018.
 */

public class News {

    private String news_post, news_poster;
    private Bitmap poster_profile_pic;

    //TODO EXIBIR HORA POSTADA

    public News(String news_post, String news_poster, Bitmap poster_profile_pic) {
        this.news_post = news_post;
        this.news_poster = news_poster;
        this.poster_profile_pic = poster_profile_pic;
    }

    public Bitmap getPoster_profile_pic() {
        return poster_profile_pic;
    }

    public String getNews_poster() {
        return news_poster;
    }

    public String getNews_post() {
        return news_post;
    }

}
