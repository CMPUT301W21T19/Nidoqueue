package com.example.nidoqueue;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AbstractActivity {
    ImageButton options;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();

    private View.OnClickListener Options = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signInOptions();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);

        options = findViewById(R.id.options_button);
        options.setOnClickListener(Options);
    }
}
