package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    // initialize RequestManager
    RequestManager requestManager = new RequestManager();

    // initialize ExperimentManager
    ExperimentManager experimentManager = new ExperimentManager();

    // initialize UserControl
    UserControl userControl = new UserControl();

    // initialize QAForum
    // TO DO

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome();
    }
    public void welcome(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}