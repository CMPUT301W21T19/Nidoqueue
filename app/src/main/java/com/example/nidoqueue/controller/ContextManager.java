package com.example.nidoqueue.controller;

import android.content.Context;

import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.model.DatabaseManager;

/**
 * Classname:   ContextManager.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This handles the contexts that are set and retrieved throughout the program.
 */
public class ContextManager {

    // Singleton Pattern
    private static final ContextManager contextManager = new ContextManager();

    private ContextManager(){}
    public static final ContextManager getInstance() {
        return contextManager;
    }
    // Get instances of other Singleton classes needed
    //    private static final UserControl userControl = UserControl.getInstance();
    //    private static final ExperimentManager experimentManager = ExperimentManager.getInstance();
    //    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    //    private static final ContextManager contextManager = ContextManager.getInstance();
    AbstractActivity context;
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