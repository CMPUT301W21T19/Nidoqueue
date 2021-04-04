package com.example.nidoqueue.controller;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class GeneralManager {
    private static GeneralManager generalManager = new GeneralManager();
    public static GeneralManager getInstance() {
        return generalManager;
    }
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final Database database = Database.getInstance();

    public void verifyLogin() {
        requestManager.transition(R.layout.welcome_main, WelcomeActivity.class);
    }
    public void home() {
        requestManager.transition(R.layout.welcome_user, SignInActivity.class);
    }
    public void back() {
        requestManager.transition(R.layout.welcome_main, WelcomeActivity.class);
    }
    public void search() {
        requestManager. transition(R.layout.search_trials, SearchActivity .class);
    }
    public void resetApp() {
        requestManager.transition(R.layout.activity_main, MainActivity.class);
    }

}
