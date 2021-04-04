package com.example.nidoqueue.controller;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.DataCalc;
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

    /******************************************************************************
     * UserControl methods are called.
     ******************************************************************************/
    public void setUserId(User user) {
        userControl.setID(user);
    }
    public void signIn() {
        userControl.signIn();
    }
    public void signUp() {
        userControl.signUp();
    }
    public void profile() {
        userControl.profile();
    }
    /******************************************************************************
     * ExperimentManager methods are called.
     ******************************************************************************/
    public void addExp() {
        experimentManager.addExp();
    }
    public void getCurrentExp(){
        experimentManager.getCurrentExperiment();
    }
    public void getCurrentCalc(){
        experimentManager.getCurrentCalc();
    }
    /******************************************************************************
     * General methods are called.
     ******************************************************************************/
    public void startApp() {
        transition(R.layout.welcome_main, WelcomeActivity.class);
    }
    public void resetApp() {
        transition(R.layout.activity_main, MainActivity.class);
    }
    public void home() {
        transition(R.layout.welcome_user, SignInActivity.class);
    }
    public void search() {
        transition(R.layout.search_trials, SearchActivity .class);
    }
    public void back() {
        transition(R.layout.welcome_user, SignInActivity.class);
    }
    /******************************************************************************
     * Dead Code --- Dead Code --- Dead Code
     ******************************************************************************/
    /**
    public void addExperiment(Experiment exp, String type) {
        experimentManager.addExp(exp, type);
    }
    */
}
