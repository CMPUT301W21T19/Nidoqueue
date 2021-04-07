package com.example.nidoqueue.controller;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.nidoqueue.activity.SignUpFragment;
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
        //testApp();
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
     * Test methods are called.
     ******************************************************************************/
    public void testApp() {
        SignUpFragment test = new SignUpFragment("", "", "", "", false);
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String misMatch = "123456790";
        String[] username = {small, large, normal, normal, normal, normal, normal, normal};
        String[] email = {normal, normal, small, large, normal, normal, normal, normal};
        String[] password = {normal, normal, normal, normal, small, large, normal, normal};
        String[] passwordRe = {normal, normal, normal, normal, normal, normal, misMatch, normal};
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username", "Test 3: Short Email", "Test 4: Long Email",
                "Test 5: Short Password", "Test 6: Long Password", "Test 7: Password Mismatch", "Test 8: No Errors"};

        for(int testNum = 0; testNum < username.length; testNum++){
            Toast.makeText(contextManager.getActivity().getApplicationContext(), testMessage[testNum], Toast.LENGTH_SHORT).show();
            test.trySignUp(username[testNum], email[testNum],password[testNum],passwordRe[testNum], true);
        }
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
