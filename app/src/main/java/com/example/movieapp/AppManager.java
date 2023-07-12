package com.example.movieapp;

import com.example.movieapp.models.User;

public class AppManager {
    private static AppManager appManager = null;
    private DBManager dbManager;
    private User loggedIn;


    private AppManager() {
    }

    public static void init() {
        if (appManager == null) appManager = new AppManager();
    }

    public static AppManager getInstance() {
        return appManager;
    }

    private DBManager getDBManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(User loggedIn) {
        this.loggedIn = loggedIn;
    }
}
