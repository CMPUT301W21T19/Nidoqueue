package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.Database;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   MainActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Functions as an automatic transition over to the either the Welcome screen or the title screen (Sign up, Sign In, Recover Account)
 * Issues:      No issues, however this needs to be tested.
 */

public class MainActivity extends AbstractActivity {
    // initialize RequestManager
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    Database database = Database.getInstance();


    private View.OnClickListener SignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp();
        }
    };

    private View.OnClickListener SignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signIn();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(MainActivity.this);
        setContentView(R.layout.activity_main);
        requestManager.startApp();
    }
    public FirebaseFirestore getDB() {
        return database.getDb();
    }
}

