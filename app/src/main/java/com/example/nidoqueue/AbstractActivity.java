package com.example.nidoqueue;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity implements ActivityContext {
    @Override
    public Context getContext() {
        return this;
    }
}
