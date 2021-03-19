package com.example.nidoqueue.controller;

import android.content.Context;

public class ContextManager {
    private static ContextManager contextManager = new ContextManager();

    Context context;

    private ContextManager(){}

    public static ContextManager getInstance() {
        return contextManager;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
