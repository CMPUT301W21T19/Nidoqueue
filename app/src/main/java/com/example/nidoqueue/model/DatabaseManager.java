package com.example.nidoqueue.model;

import android.annotation.SuppressLint;
import android.provider.Settings;
import android.util.Log;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.documentCallBack;
import com.example.nidoqueue.controller.documentSuccessCallBack;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   DatabaseManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Sets up the Firestore which handles our database and retrieving user information.
 * Issues:      No issues currently.
 */
public class DatabaseManager {
    FirebaseFirestore db;
    DocumentSnapshot document;
    String android_id;
    User user;

    private static final DatabaseManager databaseManager = new DatabaseManager();
    private static final ContextManager contextManager = ContextManager.getInstance();

    @SuppressLint("HardwareIds")
    private DatabaseManager() {
        db = FirebaseFirestore.getInstance();
    }

    public static DatabaseManager getInstance() {
        return databaseManager;
    }

    static ArrayList<Experiment> createdExps = new ArrayList<Experiment>();
    static ArrayList<Experiment> subscribedExps = new ArrayList<Experiment>();


    public void setUser(User user) {
        this.user = user;
    }

    /**
     * public void updateUser() {
     * getDb().collection("users")
     * .document(getAndroid_id())
     * .get()
     * .addOnCompleteListener(task -> {
     * if (task.isSuccessful()) {
     * Log.d("FireStore", "Success");
     * DocumentSnapshot document = task.getResult();
     * if (document.exists()) {
     * String userName = document.getString("userName");
     * String email = document.getString("email");
     * String phoneNumber = document.getString("phoneNumber");
     * setUser(new User(userName, email, phoneNumber));
     * }
     * } else {
     * Log.d("FireStore", "Failed with: ", task.getException());
     * }
     * });
     * }
     */
    public User getUser() {
        return this.user;
    }
    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }
    public String getAndroid_id() {
        return android_id;
    }

    public FirebaseFirestore getDb() {
        return db;
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

    public ArrayList<Experiment> getCreatedExps() {
        return createdExps;
    }

    public void setCreatedExps() {
//        createdExps.add(new ExpBinomial(databaseManager.getUser(), "name", "descript", false));
    }
}
