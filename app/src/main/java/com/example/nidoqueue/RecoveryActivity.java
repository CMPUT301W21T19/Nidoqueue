package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecoveryActivity extends AbstractActivity {
    ImageButton backButton;
    Button sendButton;

    private View.OnClickListener Send = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        }
    };

    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_recovery);
        backButton = findViewById(R.id.back_button2);
        sendButton = findViewById(R.id.send_button);
        backButton.setOnClickListener(Back);
        sendButton.setOnClickListener(Send);
    }


}
