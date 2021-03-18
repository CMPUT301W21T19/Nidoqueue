package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUpActivity extends AppCompatActivity{
    private Button doneButton;
    private ImageButton backButton;
    private EditText username, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        doneButton = findViewById(R.id.done_button);
        backButton = findViewById(R.id.back_button);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone();
            }
        });
    }
    public void done(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void username(){
        
    }
    public void email(){

    }
    public void phone(){

    }
}