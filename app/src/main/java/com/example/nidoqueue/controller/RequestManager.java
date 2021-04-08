package com.example.nidoqueue.controller;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.nidoqueue.activity.ExpListAdapter;
import com.example.nidoqueue.activity.ExperimentCreateFragment;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.DataCalc;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.UserProfileActivity;

import static com.example.nidoqueue.controller.UserControl.contextManager;
import static com.example.nidoqueue.controller.UserControl.databaseManager;

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
    private static final DatabaseManager database = DatabaseManager.getInstance();

    // Transition between Activities
    public <T extends AbstractActivity> void transition(Class<T> nextActivity) {
        AbstractActivity currentActivity = (AbstractActivity)  contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
    }
    // Transition between Activities with position parameter
    public <T extends AbstractActivity> void transition(Class<T> nextActivity, int position) {
        AbstractActivity currentActivity = (AbstractActivity)  contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.putExtra("ListPosition", position);
        currentActivity.startActivity(intent);
    }

    /******************************************************************************
     * UserControl methods are called.
     ******************************************************************************/
    public void setUserId(User user) {
        //userControl.setID(user);
    }
    public void signIn() {
        userControl.signIn();
    }
    public void signUp() {
        userControl.signUp();
    }
    public void clickHere() {
        userControl.clickHere();
    }
    public void profile() {
        userControl.profile();
    }
    public void edit() {
        userControl.edit();
    }
    public void select() {
        userControl.select();
    }
    /******************************************************************************
     * ExperimentManager methods are called.
     ******************************************************************************/
    public void createExp() {
        new ExperimentCreateFragment(null, null, null, null, null, null, database).show(contextManager.getActivity().getSupportFragmentManager(), "Create Exp");
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
        // Set user
        String username = "NameNameName";
        String email = "EmailEmailEmail";
        String password = "9994445555";
        databaseManager.setUser(new User(username, email, password, null, null));
        transition(WelcomeActivity.class);
    }
    public void resetApp() {
        transition(MainActivity.class);
    }
    public void home() {
        transition(SignInActivity.class);
    }
    public void search() {
        transition(SearchActivity .class);
    }
    public void back() {
        transition(SignInActivity.class);
    }
    public void addExperiment(Experiment exp, String type, ExpListAdapter expListAdapter) {
        expListAdapter.list.add(exp);
    }

    public void sub_exp(Experiment experiment) {
        databaseManager.getUser().addSubscribedExp(experiment);
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
