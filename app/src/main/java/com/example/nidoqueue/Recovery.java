package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Recovery extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_recovery);
        Intent intent = getIntent();
    }
    public void backMain(View view){
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(MainActivity.this, SignIn.class);
        //startActivity(intent);
    }
}
