package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecoveryActivity extends AppCompatActivity {
    ImageButton backButton;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_recovery);
        backButton = findViewById(R.id.back_button2);
        sendButton = findViewById(R.id.send_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }
    public void send(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Intent intent = getIntent();
    }
    public void backMain(View view){
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(MainActivity.this, SignIn.class);
        //startActivity(intent);
    }
}