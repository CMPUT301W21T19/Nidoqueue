package com.example.nidoqueue;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {

    public Context getContext() {
        return this;
    }
}
