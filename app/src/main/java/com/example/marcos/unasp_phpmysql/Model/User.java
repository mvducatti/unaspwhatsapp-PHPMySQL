package com.example.marcos.unasp_phpmysql.Model;


public class User {

    private int id;
    private String email;
    private String name;

    private String photo;

    public User(int id,String email, String name, String photo) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }



}
