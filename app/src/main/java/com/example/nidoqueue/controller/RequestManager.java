package com.example.nidoqueue.controller;

import android.content.Intent;
import android.provider.Settings;

import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.UserProfileActivity;

import static com.example.nidoqueue.controller.UserControl.contextManager;

public class RequestManager {

    // Singleton pattern
    private static final RequestManager requestManager = new RequestManager();
    private RequestManager(){}
    public static RequestManager getInstance() {
        return requestManager;
    }

    // Get instances of other Singleton classes needed
    private static final UserControl userControl = UserControl.getInstance();
    private static final ExperimentManager experimentManager = ExperimentManager.getInstance();
    private static final Database database = Database.getInstance();

    // Transition between Activities
    public <T extends AbstractActivity> void transition(int layout, Class<T> nextActivity) {
        AbstractActivity currentActivity = (AbstractActivity)  contextManager.getContext();

        currentActivity.setContentView(layout);
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
    }

    // Application begins with login attempt
    public void startApp() {
        userControl.verifyLogin();
        String android_id = Settings.Secure.getString(contextManager.getActivity().getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        database.setAndroid_id(android_id);
    }

    public void signIn() { userControl.signIn(); }

    public void signUp() { userControl.signUp(); }

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

    public void setUserDB() {
        userControl.setUserDB();
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
