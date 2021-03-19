package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AbstractActivity {
    // initialize RequestManager
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();


    //initialize QAForum
    //TO DO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestManager.startApp();
    }
}
