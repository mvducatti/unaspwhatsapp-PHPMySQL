package com.example.marcos.unasp_phpmysql.Model;


public class User {

    private int id;
    private String email, password;

    public User(int id,String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail() {
        return email;
    }



}
