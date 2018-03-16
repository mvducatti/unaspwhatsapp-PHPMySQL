package com.example.marcos.unasp_phpmysql.PHP;

import com.example.marcos.unasp_phpmysql.Model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Marcos Ducatti on 15/03/2018.
 */

public interface APIService {

    //The register call
    @FormUrlEncoded
    @POST("android/registerUser.php")
    Call<Result> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password);

}