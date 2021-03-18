package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ExperimentDataActivity extends AppCompatActivity {

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment_data);
        Intent intent = getIntent();
    }
}
