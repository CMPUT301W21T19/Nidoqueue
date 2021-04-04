package com.example.nidoqueue.controller;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

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
    private static final GeneralManager generalManager = GeneralManager.getInstance();
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
     * GeneralManager methods are called.
     ******************************************************************************/
    public void startApp() {
        generalManager.verifyLogin(); // Application begins with login attempt
    }
    public void resetApp() {
        generalManager.resetApp();
    }
    public void home() {
        generalManager.home();
    }
    public void search() {
        generalManager.search();
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
