package com.example.marcos.unasp_phpmysql.ProductsAPI;

import com.example.marcos.unasp_phpmysql.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ProductAPI {

    private static final String listAllProducts = "http://10.2.7.50/android/v1/";

    public interface PostService{
        @GET("getAllNews.php")
        Call<List<News>> getAllNews(@Path("") String id);
    }
}
