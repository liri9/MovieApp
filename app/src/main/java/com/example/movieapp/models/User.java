package com.example.movieapp.models;

import java.util.ArrayList;

public class User {
    private String userName;
    private String name;
    private String phone;
    private String id;
    private ArrayList<Categories> likedCategories;

    public User() {
        likedCategories= new ArrayList<Categories>();
    }
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

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
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
    public void addCategory (String category){

        likedCategories.add(getCategoryFromString(category));
    }
    public void removeCategory (String category){
        likedCategories.remove(getCategoryFromString(category));

    }
    public Categories getCategoryFromString(String categoryName) {
        for (Categories category : Categories.values()) {
            if (category.name().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null; // Category not found
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
