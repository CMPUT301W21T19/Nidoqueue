package com.example.nidoqueue;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   DatabaseManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Sets up the Firestore which handles our database and retrieving user information.
 * Issues:      No issues currently.
 */


interface documentCallBack {
    void isDocumentExist(boolean exist);
}


public class DatabaseManager extends Application {

    FirebaseFirestore db;
    String android_id;
    User user;
    Boolean docExist;
    DocumentSnapshot document;

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseFirestore.getInstance();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        updateUser();
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
                        if(documentSnapshot.exists()) {
                            Log.d("FireStore", "Document exists!");
                            this.document = documentSnapshot;
                            documentCallBack.isDocumentExist(true);
                        } else {
                            Log.d("FireStore", "Document does not exists!");
                            documentCallBack.isDocumentExist(false);
                        }
                    }else {
                        documentCallBack.isDocumentExist(false);
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
        //return docExist;
    }
}