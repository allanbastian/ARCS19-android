package com.ieeecsvit.arcs19;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Arcs19Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);         //Firebase caching
    }
}
