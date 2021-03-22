package com.example.nidoqueue;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classname:   AbstractActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Replaces AppCompactActivity, in which our Activities extend                  this class instead. Places a big part in the overall functionality.
 * Issues:	   No issues currently.
 */

public abstract class AbstractActivity extends AppCompatActivity {
    public Context getContext() {
        return this;
    }
}
