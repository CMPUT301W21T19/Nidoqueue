package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.google.firebase.firestore.FirebaseFirestore;

public class qrSelectorCount extends AbstractActivity{

    //region Class Variables
    private ImageButton btn_home, btn_back;
    private Button btn_add, btn_share;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_count);

        btn_back = findViewById(R.id.back_button_qr_count);
        btn_home = findViewById(R.id.home_button_qr_count);
        btn_share = findViewById(R.id.btn_qr_count_share);
        btn_add = findViewById(R.id.btn_qr_count_increase);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_share.setOnClickListener(share);
        btn_add.setOnClickListener(add);
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

    private View.OnClickListener add = new View.OnClickListener() {
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
