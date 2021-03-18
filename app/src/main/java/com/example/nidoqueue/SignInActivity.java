package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    ImageButton options, search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        options = findViewById(R.id.options_button);
        search = findViewById(R.id.search_button1);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }
    public void options(){
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
    public void search(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
