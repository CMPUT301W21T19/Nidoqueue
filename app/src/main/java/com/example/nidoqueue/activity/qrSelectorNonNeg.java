package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   qrSelectorNonNeg.java
 * version:     prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity for selecting QR code type
 * issues:      lacking database functionality
 *              the back button lack functionality
 *              QR codes need some sort of link
 */
public class qrSelectorNonNeg extends AbstractActivity{

    //region Class Variables
    private ImageButton btn_home, btn_back;
    private Button btn_share;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_non_neg);

        btn_back = findViewById(R.id.back_button_qr_non_neg);
        btn_home = findViewById(R.id.home_button_qr_non_neg);
        btn_share = findViewById(R.id.btn_qr_non_neg_share);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_share.setOnClickListener(share);
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

    private View.OnClickListener share = new View.OnClickListener() {
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
    //endregion
    //region Database
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion
}