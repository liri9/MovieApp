package com.example.movieapp.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Session {

    private String id;
    private Group group;
    private int size;
    HashMap <Movie,Integer> movies = new HashMap<Movie, Integer>();
    ArrayList<User> userList = new ArrayList<>();

    public Session(Group group) {
        id= UUID.randomUUID().toString();
        this.group = group;
        size = group.getSize();
        setUserList();
    }
    public Session() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<Movie, Integer> getMovies() {
        return movies;
    }

    public void setMovies(HashMap<Movie, Integer> movies) {
        this.movies = movies;
        //todo
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList() {
        this.userList = group.getUsers();
    }

    public HashMap sessionAsHashMap(){

        HashMap<String, Object> sessionAsHashmap = new HashMap<String, Object>();
        HashMap<String, Object> users = new HashMap<String, Object>();
        for (User user:userList){
            users.put(user.getId(), )
        }
        HashMap<String, Object> likedMovies = new HashMap<String, Object>();
        users.put()
        ArrayList<String> ids = new ArrayList<String>();
        for (User user1 : users) {
            ids.add(user1.getId());
            Log.d("users id ", user1.getId());
        }

        sessionAsHashmap.put("id", id);
        sessionAsHashmap.put("USERS",users);

        groupAsHashmap.put("userIDs", ids);

        return sessionAsHashmap;
    }
}
