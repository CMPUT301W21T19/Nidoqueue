package com.example.nidoqueue.model;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.ExperimentManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   DatabaseManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Sets up the Firestore which handles our database and retrieving user information.
 * Issues:      No issues currently.
 */
public class DatabaseManager {
    FirebaseFirestore db;
    String android_id;
    User user;

    private static final DatabaseManager databaseManager = new DatabaseManager();
    private static final ContextManager contextManager = ContextManager.getInstance();

    private DatabaseManager() {
        db = FirebaseFirestore.getInstance();
    }
    public static DatabaseManager getInstance() {
        return databaseManager;
    }


    public void setUser(User user) {
        this.user = user;
    }
    /**
    public void updateUser() {
        getDb().collection("users")
                .document(getAndroid_id())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("FireStore", "Success");
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String userName = document.getString("userName");
                            String email = document.getString("email");
                            String phoneNumber = document.getString("phoneNumber");
                            setUser(new User(userName, email, phoneNumber));
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }
    */
    public User getUser() {
//        updateUser();
        return user;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }
}
