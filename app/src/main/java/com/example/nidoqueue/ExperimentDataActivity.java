package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.nidoqueue.Experiment;
import com.example.nidoqueue.R;
import com.example.nidoqueue.User;

public class ExperimentDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment_data);
        Intent intent = getIntent();
    }
}
