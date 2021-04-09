package com.example.nidoqueue.controller;

import android.content.Context;

import com.example.nidoqueue.activity.AbstractActivity;
/**
 * Classname:   ContextManager.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This handles the contexts that are set and retrieved throughout the program.
 */
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
    public AbstractActivity getActivity() {
        return context;
    }
    public static class ExperimentManager {
    }
}