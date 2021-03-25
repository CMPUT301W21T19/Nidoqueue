package com.example.nidoqueue.controller;

import android.util.Log;

import com.example.nidoqueue.model.Experiment;
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

    private static final DatabaseManager databaseManager = new DatabaseManager();

    FirebaseFirestore db;
    String android_id;
    User user;
    DocumentSnapshot document;


    DatabaseManager() {
//        android_id = Settings.Secure.getString(contextManager.getActivity().getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//        updateUser();
    }

    public static DatabaseManager getInstance() {
        return databaseManager;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public User getUser() {
        updateUser();
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

    public DocumentSnapshot getDocument() {
        return document;
    }

    public void checkDocument(String collection, String document, documentCallBack documentCallBack) {
        db.collection(collection)
                .document(document)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            Log.d("FireStore", "Document exists!");
                            this.document = documentSnapshot;
                            documentCallBack.isDocumentExist(true);
                        } else {
                            Log.d("FireStore", "Document does not exists!");
                            documentCallBack.isDocumentExist(false);
                        }
                    } else {
                        documentCallBack.isDocumentExist(false);
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void setDocument(String collection, String document, Experiment exp, documentSuccessCallBack documentSuccessCallBack) {
        db.collection(collection)
                .document(document)
                .set(exp)
                .addOnCompleteListener(task -> {
                    documentSuccessCallBack.isSuccessful(task.isSuccessful());
                });
    }

    public void setDocument(String collection, String document, User user, documentSuccessCallBack documentSuccessCallBack) {
        db.collection(collection)
                .document(document)
                .set(user)
                .addOnCompleteListener(task -> {
                    documentSuccessCallBack.isSuccessful(task.isSuccessful());
                });
    }
}
