package com.example.nidoqueue.controller;

import android.content.Intent;

import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.ExpListAdapter;
import com.example.nidoqueue.activity.ExperimentCreateFragment;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;

import static com.example.nidoqueue.controller.UserControl.contextManager;
import static com.example.nidoqueue.controller.UserControl.databaseManager;
/**
 * Classname:   RequestManager.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This handles the activity transitions throughout the program.
 */
public class RequestManager {

    // Singleton pattern
    private static final RequestManager requestManager = new RequestManager();

    private RequestManager() {
    }

    public static RequestManager getInstance() {
        return requestManager;
    }

    // Get instances of other Singleton classes needed
    private static final UserControl userControl = UserControl.getInstance();
    private static final ExperimentManager experimentManager = ExperimentManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();

    // Transition between Activities
    public <T extends AbstractActivity> void transition(Class<T> nextActivity) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
    }

    // Transition between Activities with position parameter
    public <T extends AbstractActivity> void transition(Class<T> nextActivity, int position) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
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

    public void trySignIn(User user) {
        userControl.trySignIn(user);
    }

    public void signUp() {
        userControl.signUp();
    }

    public void trySignUp(User newUser) {
        userControl.trySignUp(newUser);
    }

    public void tryRecovery(User newUser) {
        userControl.tryRecovery(newUser);
    }
    public void tryEdit(User newUser) {
        userControl.tryEdit(newUser);
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

    public void searchBar() {
        userControl.searchBar();
    }
    /******************************************************************************
     * ExperimentManager methods are called.
     ******************************************************************************/
    public void createExp() {
        new ExperimentCreateFragment(null, null, null, null, null, null, database).show(contextManager.getActivity().getSupportFragmentManager(), "Create Exp");
    }

    public void getCurrentExp() {
        experimentManager.getCurrentExperiment();
    }

    public void getCurrentCalc() {
        experimentManager.getCurrentCalc();
    }

    /******************************************************************************
     * General methods are called.
     ******************************************************************************/
    public void startApp() {
        databaseManager.checkDocument("users", databaseManager.getAndroid_id(), exist -> {
            if (exist) {
                DocumentSnapshot documentSnapshot = databaseManager.getDocument();
                String username = documentSnapshot.getString("username");
                String email = documentSnapshot.getString("email");
                String password = documentSnapshot.getString("password");
                databaseManager.setUser(new User(username, email, password, null, null));
                transition(SignInActivity.class);
            } else {
                transition(WelcomeActivity.class);
            }
        });
    }

    public void resetApp() {
        transition(MainActivity.class);
    }

    public void home() {
        transition(SignInActivity.class);
    }

    public void search() {
        transition(SearchActivity.class);
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
