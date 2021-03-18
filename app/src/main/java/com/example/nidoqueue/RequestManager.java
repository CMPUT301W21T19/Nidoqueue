package com.example.nidoqueue;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class RequestManager {
    public void startApp() {
        UserControl.verifyLogin();
    }

    // Transition between Activities
    public <T extends AbstractActivity> void transition(int layout, AbstractActivity currentActivity, Class<T> nextActivity) {
        currentActivity.setContentView(layout);
        Intent intent = new Intent(currentActivity.getContext(), nextActivity);
        currentActivity.startActivity(intent);
    }


}
