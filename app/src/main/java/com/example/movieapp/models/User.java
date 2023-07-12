package com.example.movieapp.models;

import java.util.ArrayList;

public class User {
    private String userName;
    private String name;
    private String phone;
    private String id;
    private ArrayList<Categories> likedCategories;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    private ArrayList<Categories> favCategories;
    private ArrayList<Group> groups;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;

    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;

    }

    public ArrayList<Categories> getFavCategories() {
        return favCategories;
    }

    public void setFavCategories(ArrayList<Categories> favCategories) {
        this.favCategories = favCategories;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
