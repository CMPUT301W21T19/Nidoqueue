package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   exp_countActivity.java
 * version:     prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity for count experiments
 * issues:      lacking database functionality
 *              the done and back button lack functionality
 *              QR codes need some sort of link
 */
public class exp_countActivity extends AbstractActivity{

    //region class variables
    private ImageButton btn_back, btn_home;
    private Button btn_QR, btn_done;

    private TextView txt_result;
    private Button btn_add;

    private ExpCount expCount;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exp_count);

        expCount = new ExpCount(); //todo need database connection

        btn_back = findViewById(R.id.back_button_count);
        btn_home = findViewById(R.id.home_button_count);
        btn_QR = findViewById(R.id.QR_button_count);
        btn_done = findViewById(R.id.done_button_count);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_QR.setOnClickListener(QRtap);
        btn_done.setOnClickListener(DoneTap);

        txt_result = findViewById(R.id.textView_countDisplay);
        btn_add = findViewById(R.id.add_button_count);

        txt_result.setText(expCount.getCount());
        btn_add.setOnClickListener(addCount);
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
            requestManager.transition(qrSelectorCount.class);
        }
    };

    private View.OnClickListener DoneTap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo
        }
    };

    private View.OnClickListener addCount = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            expCount.increaseCount();
            txt_result.setText(expCount.getCount());
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