package com.example.movieapp.init;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MySignal.init(this);
        AppManager.init();
//        MySP.init(this);
//        MyGPS.init(this);
//        GameManager.init();
    }
}