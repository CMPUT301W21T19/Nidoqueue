package com.example.nidoqueue;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Classname:   ExperimentActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the experiments. OnClickListeners are housed here.
 * Issues:      Not actived yet.
 */
public class ExperimentActivity extends AbstractActivity {

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
    private View.OnClickListener Add = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    private View.OnClickListener Remove = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment);
        Intent intent = getIntent();
    }
}