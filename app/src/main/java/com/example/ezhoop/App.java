package com.example.ezhoop;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

public class App extends Application {
    private static App singleton;
    public FirebaseAuth firebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        initFirebase();
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public App getInstance() {
        return singleton;
    }
}
