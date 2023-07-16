package com.example.movieapp.models;

import android.util.Log;

import com.example.movieapp.init.MyRTFB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Session {

    private String id;
    private Group group;
    private int size;
    HashMap<Movie, Integer> movies = new HashMap<Movie, Integer>();
    ArrayList<User> userList = new ArrayList<>();
    private HashMap<String, Object> sessionAsHashmap = new HashMap<String, Object>();
    private HashMap<String, Object> users = new HashMap<String, Object>();
    private HashMap<String, Integer> likedMovies = new HashMap<String, Integer>();
    private ArrayList<String> matches = new ArrayList<>();
    private boolean isDone = false;


    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Session(Group group) {
        id = UUID.randomUUID().toString();
        this.group = group;
        size = group.getSize();
        setUserList();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public HashMap<String, Object> getSessionAsHashmap() {
        return sessionAsHashmap;
    }

    public void setSessionAsHashmap(HashMap<String, Object> sessionAsHashmap) {
        this.sessionAsHashmap = sessionAsHashmap;
    }

    public HashMap<String, Object> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Object> users) {
        this.users = users;
    }

    public HashMap<String, Integer> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(HashMap<String, Integer> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<String> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<String> matches) {
        this.matches = matches;
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

    public String checkMatch() {
        String key = getKeyByValue(likedMovies, size);
        if (key != null) {
            matches.add(key);
            updateMatchFB();
            return key;
        } else return null;
    }

    private void updateMatchFB() {
        MyRTFB.updateMatch(matches, id, group.getId());
    }

    private void finishSession() {
        //todo
    }

    public static String getKeyByValue(HashMap<String, Integer> hashMap, int x) {
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == x) {
                return entry.getKey();
            }
        }
        return null;  // Value not found in the HashMap
    }

    public void addLike(User user, Movie movie) {
        if (likedMovies != null) {
            if (!likedMovies.containsKey(movie.getName())) {
                likedMovies.put(movie.getName(), 1);
            } else {
                int like = likedMovies.get(movie.getName());
                likedMovies.put(movie.getName(), like + 1);
            }
        } else likedMovies.put(movie.getName(), 1);
        MyRTFB.addLikeToMovie(group.getId(), user.getId(), movie.getName(), this, likedMovies);
        checkMatch();

    }

    public HashMap sessionAsHashMap() {

        for (User user : userList) {

            HashMap<String, Object> userData = new HashMap<>();
            userData.put("MOVIES", new ArrayList<>());
            users.put(user.getId(), userData);
        }
        sessionAsHashmap.put("id", id);
        sessionAsHashmap.put("USERS", users);
        sessionAsHashmap.put("LIKED", likedMovies);
        sessionAsHashmap.put("isDone", false);

        Log.d("users in here", userList.toString());
        Log.d("the hashhh ", sessionAsHashmap.toString());
        return sessionAsHashmap;
    }
}
