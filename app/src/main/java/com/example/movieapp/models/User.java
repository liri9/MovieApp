package com.example.movieapp.models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String userName;
    private String name;
    private String phone;
    private String id;
    private final ArrayList<Categories> likedCategories = new ArrayList<Categories>();

    public User() {
    }

    public HashMap<String, Object> userAsHashmap() {
        HashMap<String, Object> userAsHashmap = new HashMap<String, Object>();
        userAsHashmap.put("userName", userName);
        userAsHashmap.put("name", name);
        userAsHashmap.put("phone", phone);
        userAsHashmap.put("id", id);
        userAsHashmap.put("categories",getCategoriesAsString());

        return userAsHashmap;
    }

    public ArrayList<String> getCategoriesAsString() {
        ArrayList<String> favoriteCats = new ArrayList<String>();


        if (!likedCategories.isEmpty()) {
            for (final Categories favCategory : likedCategories) {
                if (favCategory != null) {
                    favoriteCats.add(favCategory.toString());
                }
            }
        }
        return favoriteCats;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

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

    public void addCategory(String category) {

        likedCategories.add(getCategoryFromString(category));
    }

    public void removeCategory(String category) {
        likedCategories.remove(getCategoryFromString(category));

    }

    public ArrayList<Categories> getLikedCategories() {
        return likedCategories;
    }


    public Categories getCategoryFromString(String categoryName) {
        for (Categories category : Categories.values()) {
            if (category.name().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null; // Category not found
    }


    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
