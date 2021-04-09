package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import static java.lang.String.valueOf;

/**
 * Classname:   TrialActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity to show the trials to the user.
 */
public class TrialActivity extends AbstractActivity implements TrialFragment.OnFragmentInteractionListener{
    // initialize Managers & Database
    ImageButton backButton, homeButton, countdownButton, countButton, countFailButton, countPassButton;
    Button QRButton, doneButton;
    TextView text1, text2;
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
            countdownButton = findViewById(R.id.countdown_button);
            countButton = findViewById(R.id.count_button);
            countdownButton.setOnClickListener(Countdown);
            countButton.setOnClickListener(Count);

            QRButton = findViewById(R.id.QR2);
            doneButton = findViewById(R.id.done_button3);
            QRButton.setOnClickListener(Back);
            doneButton.setOnClickListener(Back);

            text1 = findViewById(R.id.eventText);
            String text = "Event : " + experiment.getCount();
            text1.setText(text);
        } else if (typeSelected.equals("binomial")) {
            homeButton = findViewById(R.id.home_button10);
            backButton = findViewById(R.id.back_button13);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            QRButton = findViewById(R.id.QR3);
            doneButton = findViewById(R.id.done_button4);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);

            countPassButton = findViewById(R.id.count_button3);
            countPassButton.setOnClickListener(CountPass);

            countFailButton = findViewById(R.id.count_button4);
            countFailButton.setOnClickListener(CountFail);
            text1 = findViewById(R.id.eventText3);
            text2 = findViewById(R.id.eventText4);

        } else if (typeSelected.equals("nonNegative")) {
            homeButton = findViewById(R.id.home_button11);
            backButton = findViewById(R.id.back_button14);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);
            countdownButton = findViewById(R.id.countdown_button2);
            countButton = findViewById(R.id.count_button2);
            countdownButton.setOnClickListener(Countdown);
            countButton.setOnClickListener(Count);

            QRButton = findViewById(R.id.QR4);
            doneButton = findViewById(R.id.done_button5);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);

            text1 = findViewById(R.id.eventText2);
            String text = "Event : " + experiment.getCount();
            text1.setText(text);

        } else if (typeSelected.equals("measurement")) {
            homeButton = findViewById(R.id.home_button8);
            backButton = findViewById(R.id.back_button11);
            backButton.setOnClickListener(Back);
            homeButton.setOnClickListener(Home);

            countButton = findViewById(R.id.count_button1);
            countButton.setOnClickListener(Measurement);

            QRButton = findViewById(R.id.QR);
            doneButton = findViewById(R.id.done_button2);
            QRButton.setOnClickListener(QR);
            doneButton.setOnClickListener(Back);

            text1 = findViewById(R.id.eventText1);
            text1 = findViewById(R.id.eventText1);
            String text = "Measurement: ";
            text1.setText(text);

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
    private View.OnClickListener Count = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            experiment.increaseCount(1);
            String text = "Event : " + experiment.getCount();
            text1.setText(text);
        }
    };
    private View.OnClickListener Countdown = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            experiment.decreaseCount(1);
            String text = "Event : " + experiment.getCount();
            text1.setText(text);
        }
    };
    // Binomial Trial listeners
    private View.OnClickListener CountPass = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            experiment.increasePass();
            String text = "Pass : " + experiment.getPass();
            text1.setText(text);
        }
    };

    private View.OnClickListener CountFail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            experiment.increaseFail();
            String text = "Fail : " + experiment.getFail();
            text2.setText(text);
        }
    };

    private View.OnClickListener Measurement = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text2 = findViewById(R.id.valueText);

            double value = Double.parseDouble(text2.getText().toString());
            experiment.addTrial(value);
            text2.setText(" ");
        }
    };
    public FirebaseFirestore getDB() {
        return null;
    }

    @Override
    public void onOkPressed(User newUser) {

    }
}
