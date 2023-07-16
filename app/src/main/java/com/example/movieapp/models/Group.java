package com.example.movieapp.models;

import android.util.Log;

import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Group {
    private String id;
    private String name;
    private ArrayList<User> users=new ArrayList<>();;
    private ArrayList<String> usersIds = new ArrayList<>();
    private int size;


    public Group(String name, ArrayList<User> users) {
        this.name = name;
        this.users = users;
    }

    public Group(){}

    public Group(String str) {
//        id = UUID.randomUUID().toString();
//        users = new ArrayList<>();
//        users.add(AppManager.getInstance().getLoggedIn());
//        AppManager.getInstance().getLoggedIn().addGroupToFB(this);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        if (id == null){
            id = UUID.randomUUID().toString();
            users.add(AppManager.getInstance().getLoggedIn());
            AppManager.getInstance().getLoggedIn().addGroupToFB(this);
        }
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

    public Group setUsersToDB(ArrayList<String> names) {
        Log.d("user adding by name",names.toString());
        for (String userName : names) {
            MyRTFB.getUserByUserName(userName, user -> {
                Log.d("user adding by name 11",user.userAsHashmap().toString());
                if (user != null) {
                    Log.d("user adding by name 12",user.userAsHashmap().toString());
                    addUser(user);
                    user.addGroupToFB(this);
                    updateGroupInFB();
                }
            });
        }
        return this;
    }
    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public void addUser(User user) {
        users.add(user);
        usersIds.add((user.getId()));
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void updateGroupInFB (){
        Log.d("user adding by name 1",usersIds.size()+"");

        MyRTFB.saveNewGroup(this);
    }
    public HashMap<String, Object> groupAsHashmap() {
        HashMap<String, Object> groupAsHashmap = new HashMap<String, Object>();
        ArrayList<String> ids = new ArrayList<String>();
        for (User user1 : users) {
            ids.add(user1.getId());
            Log.d("users id ", user1.getId());
        }
        groupAsHashmap.put("name", name);
        groupAsHashmap.put("id", id);
        groupAsHashmap.put("userIDs", ids);

        return groupAsHashmap;


    }
}
