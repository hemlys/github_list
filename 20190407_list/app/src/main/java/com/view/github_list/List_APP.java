package com.view.github_list;

import android.app.Application;

public class List_APP extends Application {
    public static List_APP sInstance;
    public static List_APP getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
