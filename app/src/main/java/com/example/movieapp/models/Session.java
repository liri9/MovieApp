package com.example.movieapp.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Session {

    private int id;
    private Group group;
    private int size;
    HashMap <Movie,Integer> movies = new HashMap<Movie, Integer>();
    ArrayList <ArrayList<User>> userList = new ArrayList<>();

    public Session(Group group) {
        this.group = group;
        size = group.getSize();
        setUserList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Movie, Integer> getMovies() {
        return movies;
    }

    public void setMovies(HashMap<Movie, Integer> movies) {
        this.movies = movies;
        //todo
    }

    public ArrayList<ArrayList<User>> getUserList() {
        return userList;
    }

    public void setUserList() {
        this.group.getUsers();
        // todo
    }
}
