package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * Classname:   TrialActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity to show the trials to the user.
 */
public class TrialActivity extends AbstractActivity implements TrialFragment.OnFragmentInteractionListener{
    // initialize Managers & Database
    ImageButton backButton, homeButton;
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    DatabaseManager databaseManager = DatabaseManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(TrialActivity.this);
        setContentView(R.layout.trials);

        homeButton = findViewById(R.id.home_buttonTrials);
        backButton = findViewById(R.id.back_buttonTrials);

        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);

    }
    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.home();
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.back();
        }
    };
    public FirebaseFirestore getDB() {
        return null;
    }

    @Override
    public void onOkPressed(User newUser) {

    }
}
