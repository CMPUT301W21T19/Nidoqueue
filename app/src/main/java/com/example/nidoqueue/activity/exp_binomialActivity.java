package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   exp_binomialActivity.java
 * version:     prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity for binomial experiments
 * issues:      lacking database functionality
 *              the done and back button lack functionality
 *              QR codes need some sort of link
 */
public class exp_binomialActivity extends AbstractActivity{

    //region class variables
    private ImageButton btn_back, btn_home;
    private Button btn_QR, btn_done;

    private TextView txt_pass, txt_fail;
    private Button btn_pass, btn_fail;

    private ExpBinomial expBinomial;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_binomial);

        expBinomial = new ExpBinomial(); //todo need database connection


        btn_back = findViewById(R.id.back_button_binomial);
        btn_home = findViewById(R.id.home_button_binomial);
        btn_QR = findViewById(R.id.QR_button_binomial);
        btn_done = findViewById(R.id.done_button_binomial);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_QR.setOnClickListener(QRtap);
        btn_done.setOnClickListener(DoneTap);

        txt_pass = findViewById(R.id.textView_passDisplay);
        txt_fail = findViewById(R.id.textView_failDisplay);
        btn_pass = findViewById(R.id.pass_button_binomial);
        btn_fail = findViewById(R.id.fail_button_binomial);

        txt_pass.setText(expBinomial.getPass());
        txt_fail.setText(expBinomial.getFail());

        btn_pass.setOnClickListener(addPass);
        btn_fail.setOnClickListener(addFail);
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
            requestManager.transition(qrSelectorBinomial.class);
        }
    };

    private View.OnClickListener DoneTap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private View.OnClickListener addPass = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            expBinomial.increasePass();
            txt_pass.setText(expBinomial.getPass());
        }
    };

    private View.OnClickListener addFail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            expBinomial.increaseFail();
            txt_fail.setText(expBinomial.getFail());
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
