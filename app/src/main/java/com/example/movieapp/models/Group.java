package com.example.movieapp.models;

import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.activities.RegistrationActivity;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Group {
    private String id;
    private String name;
    private ArrayList<User> users=new ArrayList<>();;
    private ArrayList<String> userIDs = new ArrayList<>();
    private int size;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<String> getUsersIDs() {
        return userIDs;
    }

    public void setUserIDs(ArrayList<String> usersIDs) {
        this.userIDs = usersIDs;
        for (String id :usersIDs) {
            MyRTFB.getUserData(id, user -> {
                if (user !=null){
                    users.add(user);
                }
            });
        }
    }

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
        for (String userName : names) {
            MyRTFB.getUserByUserName(userName, user -> {
                if (user != null) {
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
        userIDs.add((user.getId()));
    }

    public void removeUser(User user) {
        users.remove(user);
        //todo also from db and from ids array
    }

    public void updateGroupInFB (){
        MyRTFB.saveNewGroup(this);
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

   public String toString (){
       StringBuilder sb = new StringBuilder();
       sb.append(name);
       for (User user : users){
           sb.append(user.getName());
       }
       for (String user : userIDs){
           sb.append("mmmm" +user);
       }
       sb.append("mememe :)))");
       String result = sb.toString();
    return result;
    }
}
