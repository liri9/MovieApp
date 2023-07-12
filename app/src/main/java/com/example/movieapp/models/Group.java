package com.example.movieapp.models;

import java.util.ArrayList;

public class Group {
    private int id;
    private String name;
    private ArrayList<User> users;

    public Group(String name, ArrayList<User> users, User manager) {
        this.name = name;
        this.users = users;
        this.manager = manager;
    }

    private int size;
    private User manager;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addUser(User user){
        //todo
    }
    public void removeUser (User user){
        //todo
    }
}
