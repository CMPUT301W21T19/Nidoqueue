package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpMeasurement;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   exp_measureActivity.java
 * version:     prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity for measurement experiments
 * issues:      lacking database functionality
 *              the done and back button lack functionality
 *              QR codes need some sort of link
 */
public class exp_measureActivity extends AbstractActivity{

    //region class variables
    private ImageButton btn_back, btn_home;
    private Button btn_QR, btn_done;

    private Button btn_add;
    private EditText edt_measure;

    private ExpMeasurement expMeasurement;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_measurement);

        expMeasurement = new ExpMeasurement(); //todo need database connection

        btn_back = findViewById(R.id.back_button_measure);
        btn_home = findViewById(R.id.home_button_measure);
        btn_QR = findViewById(R.id.QR_button_measure);
        btn_done = findViewById(R.id.done_button_measure);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_QR.setOnClickListener(QRtap);
        btn_done.setOnClickListener(DoneTap);

        btn_add = findViewById(R.id.add_button_measure);
        edt_measure = findViewById(R.id.editText_measure);

        btn_add.setOnClickListener(addMeasure);
    }

    //region OnClickListeners
    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private View.OnClickListener QRtap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(qrSelectorMeasure.class);
        }
    };

    private View.OnClickListener DoneTap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private View.OnClickListener addMeasure = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double measurement = Double.parseDouble(edt_measure.getText().toString());
            expMeasurement.addTrial(measurement);
        }
    };
    //endregion


    //region Database Methods
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion
}