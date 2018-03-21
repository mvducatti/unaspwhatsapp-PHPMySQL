package com.example.marcos.unasp_phpmysql.Model;

/**
 * Created by marcos on 19/03/2018.
 */

public class News {

    private int id;
    private String news_post;

    public News(int id, String news_post) {
        this.id = id;
        this.news_post = news_post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNews_post() {
        return news_post;
    }

    public void setNews_post(String news_post) {
        this.news_post = news_post;
    }
}
