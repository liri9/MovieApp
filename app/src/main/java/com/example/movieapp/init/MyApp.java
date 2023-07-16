package com.example.movieapp.init;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MySignal.init(this);
        AppManager.init();
        Imager.initHelper(this);
    }
}