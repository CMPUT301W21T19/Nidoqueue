package com.example.nidoqueue;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class RequestManager {

    private static final RequestManager requestManager = new RequestManager();

    private RequestManager(){}

    // initialize RequestManager
    private static final UserControl userControl = UserControl.getInstance();


    public static RequestManager getInstance() {
        return requestManager;
    }

    // Transition between Activities
    public <T extends AbstractActivity> void transition(int layout, AbstractActivity currentActivity, Class<T> nextActivity) {
        currentActivity.setContentView(layout);
        Intent intent = new Intent(currentActivity.getContext(), nextActivity);
        currentActivity.startActivity(intent);
    }

    public void startApp() {
        userControl.verifyLogin();
    }

    public void signIn() { userControl.signIn(); }

    public void signUp() { userControl.signUp(); }

    public void signInOptions() { userControl.signInOptions(); }
}
