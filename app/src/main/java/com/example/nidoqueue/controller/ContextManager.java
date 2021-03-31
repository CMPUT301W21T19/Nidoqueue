package com.example.nidoqueue.controller;

import android.content.Context;

import com.example.nidoqueue.activity.AbstractActivity;

public class ContextManager {
    private static ContextManager contextManager = new ContextManager();

    AbstractActivity context;


    private ContextManager(){}

    public static ContextManager getInstance() {
        return contextManager;
    }

    public void setContext(AbstractActivity context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public AbstractActivity getActivity() { return context; }

    /**
     * Classname:   ExperimentManager.java
     * Version:     Prototype
     * Date:        March 19th, 2021
     * Purpose:     This handles experiment transitions similar to the UserControl class.
     * Issues:      Incomplete, will be worked on in the future.
     */
    public static class ExperimentManager {
    }
}