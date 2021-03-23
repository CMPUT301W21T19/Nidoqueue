package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nidoqueue.R;

public class SignUpActivity extends AppCompatActivity {
    private Button doneButton;
    private ImageButton backButton;
    private EditText username, email, phone;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();


    private View.OnClickListener Done = new View.OnClickListener() {
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

    private View.OnClickListener Username = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp_Username();
        }
    };
    private View.OnClickListener Email = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp_Email();        }
    };
    private View.OnClickListener Phone = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp_Phone();        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        contextManager.setContext(this);
        doneButton = findViewById(R.id.done_button);
        backButton = findViewById(R.id.back_button);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        doneButton.setOnClickListener(Done);
        backButton.setOnClickListener(Back);
        username.setOnClickListener(Username);
        email.setOnClickListener(Email);
        phone.setOnClickListener(Phone);
    }

}