package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   SearchActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the OnClickListeners for the search function.
 * Issues:      Incomplete
 */
public class SearchActivity extends AppCompatActivity {

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();

    FirebaseFirestore db;
    User user;
    String android_id;
    DatabaseManager dbManager;

    ArrayList<Experiment> exps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_trials);
        contextManager.setContext(this);

        dbManager = (DatabaseManager) getApplicationContext();
        db = dbManager.getDb();
        //user = dbManager.getUser();
        //android_id = dbManager.getAndroid_id();

        exps = new ArrayList<>();


    }
}
