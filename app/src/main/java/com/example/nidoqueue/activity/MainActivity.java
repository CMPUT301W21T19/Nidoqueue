package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   MainActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Functions as an automatic transition over to the either the Welcome screen or the title screen (Sign up, Sign In)
 */

public class MainActivity extends AbstractActivity {
    // initialize Managers & Database
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    DatabaseManager database = DatabaseManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(MainActivity.this);
        setContentView(R.layout.activity_main);
        requestManager.startApp();
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return database.getDb();
    }
}

