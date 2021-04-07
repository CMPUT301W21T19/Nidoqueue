package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.google.firebase.firestore.FirebaseFirestore;

public class RecoveryActivity extends AbstractActivity {
    ImageButton backButton;
    Button sendButton;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();

    private View.OnClickListener Send = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.resetApp();
        }
    };

    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.resetApp();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_recovery);
        contextManager.setContext(this);

        backButton = findViewById(R.id.back_button2);
        sendButton = findViewById(R.id.send_button);
        backButton.setOnClickListener(Back);
        sendButton.setOnClickListener(Send);
    }
    public FirebaseFirestore getDB() {
        return null;
    }


}