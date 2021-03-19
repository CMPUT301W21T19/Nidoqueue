package com.example.nidoqueue;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.nidoqueue.UserControl.contextManager;

/**
 * Classname:   RequestManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     This will handle the majority of the transitions in the first two screens.
 * Issues:      Needs to be tested.
 */
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

    public void Home() {
        //Transition to Home page
        requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), SignInActivity.class);
        //            Intent intent = new Intent(this, SignInActivity.class);
//            startActivity(intent);
    }

    public void signUp_Username() {
    }

    public void signUp_Email() {
    }

    public void signUp_Phone() {
    }

    public void signUp_Done() {
    }

    public void resetApp() {
        requestManager.transition(R.layout.activity_main, (AbstractActivity) contextManager.getContext(), MainActivity.class);
    }
}