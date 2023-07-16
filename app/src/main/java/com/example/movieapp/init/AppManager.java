package com.example.movieapp.init;

import android.util.Log;

import com.example.movieapp.DBManager;
import com.example.movieapp.models.Group;
import com.example.movieapp.models.Session;
import com.example.movieapp.models.User;

public class AppManager {
    private static AppManager appManager = null;
    private DBManager dbManager;
    private User loggedIn;
    private Group curentGroup;
    private Session currentSession;


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
        Log.d("this is 3","hi");

        return loggedIn;
    }

    public void setLoggedIn(User loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Group getCurentGroup() {
        return curentGroup;
    }

    public void setCurentGroup(Group curentGroup) {
        this.curentGroup = curentGroup;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
}
