package com.example.nidoqueue;

import android.content.Context;

/**
 * Classname:   ContextManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles the context get & set context calls. Places a big part in the overall functionality.
 * Issues:      No issues currently.
 */
public class ContextManager {
    private static ContextManager contextManager = new ContextManager();

    Context context;

    private ContextManager() {
    }

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
