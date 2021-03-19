package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AbstractActivity {
    ImageButton backButton;
    ImageButton homeButton;

    static RequestManager requestManager = RequestManager.getInstance();

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Home();
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Home();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);
        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);
    }

}
