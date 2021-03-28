package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;

import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.WelcomeActivity;

/**
 * Classname:   MainActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Functions as an automatic transition over to the either the Welcome screen or the title screen (Sign up, Sign In, Recover Account)
 * Issues:      No issues, however this needs to be tested.
 */

public class MainActivity extends AbstractActivity {
//    // initialize RequestManager
//    RequestManager requestManager = RequestManager.getInstance();
//    //ContextManager contextManager = ContextManager.getInstance();
//
//
//    //initialize QAForum
//    //TO DO
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        requestManager.startApp();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome();
    }

    public void welcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}