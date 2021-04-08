package com.example.nidoqueue.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.Experiment;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   ExperimentActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the experiments. OnClickListeners are housed here.
 * Issues:      Not actived yet.
 */
public class ExperimentActivity extends AbstractActivity {
    private Button subscribeButton, unsubscribeButton;

    private ImageButton backButton, homeButton;
    private Experiment experiment;
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);

        }
    };
    private View.OnClickListener Add = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.sub_exp(experiment);
        }
    };
    private View.OnClickListener Remove = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment);
        contextManager.setContext(this);
        Intent mIntent = getIntent();
        int listPosition = mIntent.getIntExtra("ListPosition", 0);
        experiment = SignInActivity.createdExps.get(listPosition);
        backButton = findViewById(R.id.back_button4);

        subscribeButton = findViewById(R.id.subscribe_button);
        unsubscribeButton = findViewById(R.id.unsubscribe_button);
        homeButton = findViewById(R.id.home_button);

        backButton.setOnClickListener(Back);
        subscribeButton.setOnClickListener(Add);
        unsubscribeButton.setOnClickListener(Remove);
        homeButton.setOnClickListener(Home);
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
}