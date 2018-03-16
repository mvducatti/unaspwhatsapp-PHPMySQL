package com.example.marcos.unasp_phpmysql.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marcos Ducatti on 15/03/2018.
 */

public class Result {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("username")
    private User username;

    public Result(Boolean error, String message, User username) {
        this.error = error;
        this.message = message;
        this.username = username;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUsername() {
        return username;
    }
}
