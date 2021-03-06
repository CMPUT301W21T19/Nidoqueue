package com.example.nidoqueue.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   MainActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Functions as an automatic transition over to either the WelcomeActivity or the SignInActivity.
 *              The WelcomeActivity if the user hasn't signed up yet. The SignInActivity if a user already exists on the system.
 */

public class MainActivity extends AbstractActivity {
    // initialize Managers & Database
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    DatabaseManager databaseManager = DatabaseManager.getInstance();

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(MainActivity.this);
        setContentView(R.layout.activity_main);
        databaseManager.setAndroid_id(Settings.Secure.getString(contextManager.getActivity().getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        requestManager.startApp();
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return databaseManager.getDb();
    }
}

