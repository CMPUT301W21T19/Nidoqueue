package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    ImageButton backButton;
    ImageButton searchButton;
    String Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_trials);
        Intent intent = getIntent();
        backButton = findViewById(R.id.back_button3);
        searchButton = findViewById(R.id.search_button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }
    public void back(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
    public void search(){
        Message = "Feature will be available in the official release";
        Toast.makeText(this, Message, Toast.LENGTH_LONG).show();
    }
}
