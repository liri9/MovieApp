package com.example.movieapp.models;

import android.util.Log;

import com.example.movieapp.init.MyRTFB;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String userName;
    private String name;
    private String phone;
    private String id;
    private final ArrayList<Categories> likedCategories = new ArrayList<Categories>();
    private ArrayList<Group> groups = new ArrayList<>(); //todo
    private ArrayList<String> groupsID = new ArrayList<>(); //todo

    public User() {
    }

    public HashMap<String, Object> userAsHashmap() {
        HashMap<String, Object> userAsHashmap = new HashMap<String, Object>();
        ArrayList<String> allGroups = new ArrayList<String>();

        for (Group group : groups) {
            allGroups.add(group.getId());
        }
        userAsHashmap.put("userName", userName);
        userAsHashmap.put("name", name);
        userAsHashmap.put("phone", phone);
        userAsHashmap.put("id", id);
        userAsHashmap.put("categories", getCategoriesAsString());
        userAsHashmap.put("groups", allGroups);

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

    public void addGroupToFB(Group group) {
        groups.add(group);
        groupsID.add(group.getId());
        MyRTFB.setGroupsForUser(groupsID,this);
    }
//    public void addGroupFromFB(Group group) {
//        groups.add(group);
//    }

    public void updateFB() {
        MyRTFB.saveNewUser(this);
    }

    public void getGroupsFromDB() {
        MyRTFB.getUserGroups(this.id, new MyRTFB.CB_GroupsArray() {
            @Override
            public void data(ArrayList<Group> groups) {
                if (groups != null && !groups.isEmpty()) {
                    for (Group group : groups) {
                        MyRTFB.getGroupData(group.getId(), new MyRTFB.CB_Group() {
                            @Override
                            public void data(Group detailedGroup) {
                                if (detailedGroup != null) {
                                    groups.add(group);
                                }
                            }
                        });
                        initGroupList(groups);
                    }
                }
            }
        });
    }

    private void initGroupList(ArrayList<Group> groups) {
        this.groups= groups;
    }

    public void removeFromGroup(Group group) {
        //todo
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

//    public void setGroups(ArrayList<Group> groups) {
//        this.groups = groups;
//    }
}
