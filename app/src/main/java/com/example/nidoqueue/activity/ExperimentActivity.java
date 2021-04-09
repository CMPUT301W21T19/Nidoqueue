package com.example.nidoqueue.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
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

    private Button subscribeButton, unsubscribeButton, unpublishButton, endButton, recordButton, dataButton;
    private ImageButton backButton, homeButton;

    private Experiment experiment;

    private TextView nameView;
    private TextView descriptView;
    private TextView regionView;
    private TextView trialsView;
    private TextView geoView;
    private TextView statusView;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();

    static DatabaseManager databaseManager = DatabaseManager.getInstance();

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
            requestManager.user_subExp(experiment);
        }
    };
    private View.OnClickListener Remove = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.user_unSubExp(experiment);
        }
    };
    private View.OnClickListener Unpublish = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Owner_unPubExp(experiment);
        }
    };
    private View.OnClickListener End = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Owner_endExp(experiment);
        }
    };
    private View.OnClickListener Record = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.recordTrials(experiment);
        }
    };
    private View.OnClickListener Data = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ExperimentDataActivity.class);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment);
        contextManager.setContext(this);
        Intent mIntent = getIntent();
        int listPosition = mIntent.getIntExtra("Integer", 0);
        experiment = databaseManager.getCreatedExps().get(listPosition);
        requestManager.setCurrentExp(experiment);
        backButton = findViewById(R.id.back_button4);

        subscribeButton = findViewById(R.id.subscribe_button);
        unsubscribeButton = findViewById(R.id.unsubscribe_button);
        homeButton = findViewById(R.id.home_button);
        unpublishButton = findViewById(R.id.unpublish_button);
        endButton = findViewById(R.id.end_button);
        recordButton = findViewById(R.id.record_button);
        dataButton = findViewById(R.id.data_button);

        backButton.setOnClickListener(Back);
        subscribeButton.setOnClickListener(Add);
        unsubscribeButton.setOnClickListener(Remove);
        homeButton.setOnClickListener(Home);
        unpublishButton.setOnClickListener(Unpublish);
        endButton.setOnClickListener(End);
        recordButton.setOnClickListener(Record);
        dataButton.setOnClickListener(Data);

        nameView = findViewById(R.id.exp_list_name);
        nameView.setText("Name : " + experiment.getName());

        descriptView = findViewById(R.id.exp_list_info);
        descriptView.setText("Description : " + experiment.getDescription());

        regionView = findViewById(R.id.exp_list_region);
        regionView.setText("Region : " + experiment.getRegion());
        
        trialsView = findViewById(R.id.exp_list_trials);
        trialsView.setText("Trials : " + experiment.getNum_of_trials());

        geoView = findViewById(R.id.exp_list_geolocation);
        geoView.setText("Geolocation : " + experiment.getGeoLocation());
        
        statusView = findViewById(R.id.exp_list_published);
        statusView.setText("Status : " + experiment.isPublished());



    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
}