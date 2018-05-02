package com.example.marcos.unasp_phpmysql.Model;

/**
 * Created by marcos on 19/03/2018.
 */

public class News {

    private String news_post, news_poster, poster_profile_pic;

    //TODO EXIBIR HORA POSTADA

    public News(String news_post, String news_poster, String poster_profile_pic) {
        this.news_post = news_post;
    }
    public String getPoster_profile_pic() {
        return poster_profile_pic;
    }

    public String getNews_poster() {
        return news_poster;
    }

    public String getNews_post() {
        return news_post;
    }

}
