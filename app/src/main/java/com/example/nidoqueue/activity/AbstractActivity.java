package com.example.nidoqueue.activity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {

    public Context getContext() {
        return this;
    }
}
