package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class TrialActivity extends AbstractActivity implements TrialFragment.OnFragmentInteractionListener{
    // initialize Managers & Database
    ImageButton backButton, homeButton;
    Button QRButton, doneButton;
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    DatabaseManager databaseManager = DatabaseManager.getInstance();

    Experiment experiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(TrialActivity.this);
        Intent mIntent = getIntent();
        int layout = mIntent.getIntExtra("Integer", 0);
        setContentView(layout);
        int listPosition = mIntent.getIntExtra("Integer2", 0);
        experiment = databaseManager.getCreatedExps().get(listPosition);

        String typeSelected = experiment.getType();
        if (typeSelected.equals("count")) {
            homeButton = findViewById(R.id.home_button9);
            backButton = findViewById(R.id.back_button12);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            QRButton = findViewById(R.id.QR2);
            doneButton = findViewById(R.id.done_button3);
            QRButton.setOnClickListener(Back);
            doneButton.setOnClickListener(Back);
        } else if (typeSelected.equals("binomial")) {
            homeButton = findViewById(R.id.home_button10);
            backButton = findViewById(R.id.back_button13);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            QRButton = findViewById(R.id.QR3);
            doneButton = findViewById(R.id.done_button4);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);
        } else if (typeSelected.equals("non negative")) {
            homeButton = findViewById(R.id.home_button11);
            backButton = findViewById(R.id.back_button14);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            QRButton = findViewById(R.id.QR4);
            doneButton = findViewById(R.id.done_button5);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);
        } else if (typeSelected.equals("measurement")) {
            homeButton = findViewById(R.id.home_button8);
            backButton = findViewById(R.id.back_button11);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            QRButton = findViewById(R.id.QR);
            doneButton = findViewById(R.id.done_button2);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);
        } else {
            Toast.makeText(contextManager.getContext(), "Experiment not a valid type", Toast.LENGTH_SHORT).show();
        }



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
            requestManager.transition(ExperimentActivity.class);
        }
    };
    private View.OnClickListener QR = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ExperimentActivity.class);
        }
    };
    public FirebaseFirestore getDB() {
        return null;
    }

    @Override
    public void onOkPressed(User newUser) {

    }
}
