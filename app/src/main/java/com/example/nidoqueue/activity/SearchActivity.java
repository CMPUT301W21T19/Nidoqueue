package com.example.nidoqueue.activity;

import android.os.Bundle;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   SearchActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the OnClickListeners for the search function.
 * Issues:      Incomplete
 */
import com.example.nidoqueue.R;

public class SearchActivity extends AbstractActivity {

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static Database database = Database.getInstance();
    User user;
    String android_id;


    ArrayList<Experiment> exps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_trials);
        contextManager.setContext(this);

        //user = dbManager.getUser();
        //android_id = dbManager.getAndroid_id();

        exps = new ArrayList<>();


    }
    public FirebaseFirestore getDB() {
        return null;
    }
}
