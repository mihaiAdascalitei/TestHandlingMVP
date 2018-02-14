package com.example.mihai.testhandling.utils.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by adasc on 2/13/2018.
 */

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application", "Created.");
    }

    public Context provideContext() {
        return getApplicationContext();
    }
}
