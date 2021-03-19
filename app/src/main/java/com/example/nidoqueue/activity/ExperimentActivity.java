package com.example.nidoqueue.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nidoqueue.R;

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
    // class attributes

    //private User owner;
    private String description;
    private String Region;
    private int num_of_trials;
    private boolean published;
    //private QuestionList questons;
    //private ArrayList<Trials> trials;
    //private ArrayList<User> experimenterList;

}