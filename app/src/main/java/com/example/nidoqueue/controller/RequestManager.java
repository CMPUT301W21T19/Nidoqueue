package com.example.nidoqueue.controller;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.provider.Settings;

import com.example.nidoqueue.DataCalc;
import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.UserProfileActivity;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestManager extends Application {

    // Singleton pattern
    private static RequestManager requestManager;
    private ContextManager contextManager;
    private DatabaseManager databaseManager;
    private UserControl userControl;
    private ExperimentManager experimentManager;

    @Override
    public void onCreate() {
        super.onCreate();
        requestManager = this;
        contextManager = new ContextManager();
        databaseManager = new DatabaseManager();
        userControl = new UserControl();
        experimentManager = new ExperimentManager();
    }

    public static RequestManager getInstance() {
        return requestManager;
    }

    public ContextManager getContextManager() {
        return contextManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public UserControl getUserControl() {
        return userControl;
    }

    public ExperimentManager getExperimentManager() {
        return experimentManager;
    }

    // Transition between Activities
    public <T extends AbstractActivity> void transition(int layout, Class<T> nextActivity) {
        AbstractActivity currentActivity = contextManager.getActivity();
        //currentActivity.setContentView(layout);
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
    }

    // Application begins with login attempt
    @SuppressLint("HardwareIds")
    public void startApp() {
        userControl.verifyLogin();
        databaseManager.setAndroid_id(Settings.Secure.getString(contextManager.getActivity().getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        databaseManager.setDb(FirebaseFirestore.getInstance());
    }

    public void signIn() {
        userControl.signIn();
    }

    public void signUp() {
        userControl.signUp();
    }

    // Transition to Home page
    public void Home() {
        transition(R.layout.welcome_user, SignInActivity.class);
    }

    // Transition to MainActivity
    public void resetApp() {
        transition(R.layout.activity_main, MainActivity.class);
    }

    // Transition to SearchActivity
    public void search() {
        transition(R.layout.search_trials, SearchActivity.class);
    }

    public void signInOptions() {
        transition(R.layout.user_profile, UserProfileActivity.class);
    }


    public Experiment getCurrentExp() {
        Experiment experiment = experimentManager.getCurrentExperiment();
        return (experiment);
    }

    public DataCalc getCurrentCalc() {
        DataCalc calc = experimentManager.getCurrentCalc();
        return (calc);
    }

    public void addExperiment(Experiment exp, String type) {
        experimentManager.addExp(exp, type);
    }

    public void setUserId(User user) {
        userControl.setID(user);
    }

//    public void setUserId() {
//        userControl.setID();
//    }
}
