/**
 * Classname:   AbstractActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Replaces AppCompactActivity, in which our Activities extend this class instead.
 * Issues:	    No issues currently.
 */

package com.example.nidoqueue;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
public abstract class AbstractActivity extends AppCompatActivity {
    public Context getContext() {
        return this;
    }
}
