package com.example.movieapp.models;

import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.activities.LoginActivity;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Group {
    private String id;
    private String name;
    private ArrayList<User> users;
    private int size;

    public Group(String name, ArrayList<User> users) {
        this.name = name;
        this.users = users;
    }

    public Group() {
        id = UUID.randomUUID().toString();
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Group setId(String id) {
        this.id = id;
        return this;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

//    public void setUsers(HashSet<User> users) {
//        this.users = users;
//    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Group setUsers(ArrayList<String> names) {

        for (String userName : names) {
            MyRTFB.getUserByUserName(userName, user -> {
                if (user != null) {
                    addUser(user);
                    user.addGroup(this);
                    Log.d("hello",users.toString());
                }
            });
        }
        Log.d("hello2",users.toString());
        addUser(AppManager.getInstance().getLoggedIn());
        AppManager.getInstance().getLoggedIn().addGroup(this);
        return this;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public HashMap<String, Object> groupAsHashmap() {
        HashMap<String, Object> groupAsHashmap = new HashMap<String, Object>();
        ArrayList<String> ids = new ArrayList<String>();
        for (User user1 : users) {
            ids.add(user1.getId());
        }
        groupAsHashmap.put("name", name);
        groupAsHashmap.put("id", id);
        groupAsHashmap.put("userIDs", ids);

        return groupAsHashmap;


    }
}
