package com.example.nidoqueue.controller;

import android.content.Intent;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.ExpListAdapter;
import com.example.nidoqueue.activity.ExperimentDataActivity;
import com.example.nidoqueue.activity.ExperimentCreateFragment;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.SearchActivity;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.TrialActivity;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

//import static com.example.nidoqueue.controller.UserControl.contextManager;
//import static com.example.nidoqueue.controller.UserControl.databaseManager;

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
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();


    // Transition between Activities
    public <T extends AbstractActivity> void transition(Class<T> nextActivity) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
    }

    // Transition between Activities with position parameter
    public <T extends AbstractActivity> void transition(Class<T> nextActivity, int integer) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.putExtra("Integer", integer);
        currentActivity.startActivity(intent);
    }

    // Transition between Activities with position parameter
    public <T extends AbstractActivity> void transition(Class<T> nextActivity, int integer, int integer2) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.putExtra("Integer", integer);
        intent.putExtra("Integer2", integer2);
        currentActivity.startActivity(intent);
    }


    /******************************************************************************
     * UserControl methods are called.
     ******************************************************************************/
    public void setUserId(User user) {
        user.setSubscribedExp(new ArrayList<Experiment>());
       databaseManager.setUser(user);
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
    public void searchBar() {
        userControl.searchBar();
    }
    /******************************************************************************
     * ExperimentManager methods are called.
     ******************************************************************************/
    public void createExp() {
        new ExperimentCreateFragment(null, null, null, null, null, null, databaseManager).show(contextManager.getActivity().getSupportFragmentManager(), "Create Exp");
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
//        userControl.init();
//        experimentManager.init();
//        requestManager.autoSignIn();


    }

    private void autoSignIn() {
        if(databaseManager.getUser() == null) {
            transition(WelcomeActivity.class);
        } else {
            transition(SignInActivity.class);
        }
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
        expListAdapter.notifyDataSetChanged();
    }
    public void user_subExp(Experiment experiment) {
        databaseManager.getUser().addSubscribedExp(experiment);
    }

    public void Owner_unPubExp(Experiment experiment) {
        experiment.unpublish();
    }

    public void user_unSubExp(Experiment experiment) {
        databaseManager.getUser().remSubscribedExp(experiment);
    }

    public void Owner_endExp(Experiment experiment) {
        experiment.end();
    }

    public void recordTrials(Experiment experiment) {
        String typeSelected = experiment.getType();
        if (typeSelected.equals("count")) {
            transition(TrialActivity.class, R.layout.count_based);
        } else if (typeSelected.equals("Binomial")) {
            transition(TrialActivity.class, R.layout.binomial);
        } else if (typeSelected.equals("non negative")) {
            transition(TrialActivity.class, R.layout.non_negative);
        } else if (typeSelected.equals("measurement")) {
            transition(TrialActivity.class, R.layout.measurement);
        } else {
            Toast.makeText(contextManager.getContext(), "Experiment not a valid type", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCurrentExp(Experiment experiment) {
        experimentManager.setCurrentExperiment(experiment);
    }

    public Experiment getExperiment() {
       return experimentManager.getCurrentExperiment();
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
