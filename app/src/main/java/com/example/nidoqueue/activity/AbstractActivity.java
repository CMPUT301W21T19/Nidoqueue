package com.example.nidoqueue.activity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Classname:   AbstractActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Replaces AppCompactActivity, in which our Activities extend this class instead.
 *              Places a big part in the overall functionality.
 * Issues:	    No issues currently.
 */

public abstract class AbstractActivity extends AppCompatActivity {
    public Context getContext() {
        return this;
    }
    public abstract FirebaseFirestore getDB();

    public void setDocument(String collectionPath, String android_id, OnCompleteListener<DocumentSnapshot> taskListener) {
        getDB().collection(collectionPath)
                .document(android_id)
                .get()
                .addOnCompleteListener(taskListener);
    }
    public void setCollection(String collectionPath, String android_id, OnCompleteListener<QuerySnapshot> taskListener) {
        getDB().collection(collectionPath)
                .get()
                .addOnCompleteListener(taskListener);
    }
}
