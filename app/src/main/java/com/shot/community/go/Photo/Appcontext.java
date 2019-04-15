package com.shot.community.go.Photo;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 2017/11/16.
 */

public class Appcontext extends Application {
    private static Appcontext myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static Appcontext getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return myApplication.getBaseContext();
    }
}
