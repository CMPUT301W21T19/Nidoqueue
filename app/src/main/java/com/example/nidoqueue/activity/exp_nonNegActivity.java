package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpNonNegative;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   exp_nonNegActivity.java
 * version:     prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity for non-negative experiments
 * issues:      lacking database functionality
 *              the done and back button lack functionality
 *              QR codes need some sort of link
 */
public class exp_nonNegActivity extends AbstractActivity{

    private ImageButton btn_back, btn_home;
    private Button btn_QR, btn_done;

    private Button btn_add;
    private EditText edt_nonNeg;

    private ExpNonNegative expNonNegative;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_non_negative);

        expNonNegative = new ExpNonNegative(); //todo need database connection

        btn_back = findViewById(R.id.back_button_nonNeg);
        btn_home = findViewById(R.id.home_button_nonNeg);
        btn_QR = findViewById(R.id.QR_button_nonNeg);
        btn_done = findViewById(R.id.done_button_nonNeg);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_QR.setOnClickListener(QRtap);
        btn_done.setOnClickListener(DoneTap);

        btn_add = findViewById(R.id.add_button_nonNeg);
        edt_nonNeg = findViewById(R.id.editText_nonNeg);
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
            String data = "change me"; //todo change this to a link to the database
            Bundle bundle = new Bundle();
            bundle.putString("QR", data);
            QRfragment qRfragment = new QRfragment();
            qRfragment.setArguments(bundle);
            qRfragment.show(getSupportFragmentManager(), null);
        }
    };

    private View.OnClickListener DoneTap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private View.OnClickListener addNonNeg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int count = Integer.valueOf(edt_nonNeg.getText().toString());
            expNonNegative.addTrial(count);
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