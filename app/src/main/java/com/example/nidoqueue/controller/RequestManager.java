package com.example.nidoqueue.controller;

import android.content.Intent;
import android.widget.Toast;
import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.R;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.AbstractActivity;
import com.example.nidoqueue.activity.ExpListAdapter;
import com.example.nidoqueue.activity.ExpSearchAdapter;
import com.example.nidoqueue.activity.ExperimentCreateFragment;
import com.example.nidoqueue.activity.ExperimentDataActivity;
import com.example.nidoqueue.activity.MainActivity;
import com.example.nidoqueue.activity.RecyclerViewDivider;
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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();


    private Class previousActivity;

    public <T extends AbstractActivity> void setPreviousActivity(Class<T> previousActivity) {
        this.previousActivity = previousActivity;
    }

    public <T extends AbstractActivity> Class getPreviousActivity() {
        return previousActivity;
    }

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

    public <T extends AbstractActivity> void transition(Class<T> nextActivity, String expName) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.putExtra("Experiment Name", expName);
        currentActivity.startActivity(intent);
    }

    public <T extends AbstractActivity> void transition(Class<T> nextActivity, int position, String expName) {
        AbstractActivity currentActivity = (AbstractActivity) contextManager.getContext();
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.putExtra("ListPosition", position);
        intent.putExtra("Experiment Name", expName);
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

    }

//    private void autoSignIn() {
//        if(databaseManager.getUser() == null) {
//            transition(WelcomeActivity.class);
//        } else {
//            transition(SignInActivity.class);
//        }
        ArrayList<Experiment> all_experiments = new ArrayList<>();
        databaseManager.getDb().collection("experiments")
                .get()
                .addOnCompleteListener(task -> {
                    Experiment experiment = null;
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        String expType = documentSnapshot.getString("type");
                        switch (expType) {
                            case "count":
                                experiment = documentSnapshot.toObject(ExpCount.class);
                                break;
                            case "binomial":
                                experiment = documentSnapshot.toObject(ExpBinomial.class);
                                break;
                            case "nonNegative":
                                experiment = documentSnapshot.toObject(ExpNonNegative.class);
                                break;
                            case "measurement":
                                experiment = documentSnapshot.toObject(ExpMeasurement.class);
                                break;
                        }
                        all_experiments.add(experiment);
                    }
                    databaseManager.setExperiments(all_experiments);
                });
//        userControl.init();
//        experimentManager.init();
//        transition(WelcomeActivity.class);
//    }

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
        databaseManager.checkDocument("experiments", exp.getName().toLowerCase(), exist -> {
            if (exist) {
                Toast.makeText(contextManager.getContext(), "Name already exists!\nTry other experiment name", Toast.LENGTH_LONG).show();
            } else {
                databaseManager.setDocument("experiments", exp.getName().toLowerCase(), exp, success -> {
                    Map<String, String> ownerId = new HashMap<>();
                    ownerId.put("owner", databaseManager.getAndroid_id());
                    databaseManager.getDb().collection("experiments")
                            .document(exp.getName().toLowerCase())
                            .set(ownerId, SetOptions.merge());
                    databaseManager.getDb().collection("users")
                            .document(databaseManager.getAndroid_id())
                            .update("createdExp", FieldValue.arrayUnion(exp.getName().toLowerCase()));
                    expListAdapter.list.add(exp);
                    expListAdapter.notifyDataSetChanged();
                });
            }
        });
    }

    public void user_subExp(Experiment experiment) {
        //databaseManager.getUser().addSubscribedExp(exp);
        databaseManager.getDb().collection("users")
                .document(databaseManager.getAndroid_id())
                .update("subscribedExp", FieldValue.arrayUnion(experiment.getName().toLowerCase()));
    }

    public void Owner_unPubExp(Experiment experiment) {
        databaseManager.checkDocument("experiments", experiment.getName().toLowerCase(), exist -> {
            Map<String, Boolean> published = new HashMap<>();
            experiment.unpublish();
            published.put("published", experiment.isPublished());
            databaseManager.getDb().collection("experiments")
                    .document(experiment.getName().toLowerCase())
                    .set(published, SetOptions.merge());
        });
    }

    public void user_unSubExp(Experiment experiment) {
        //databaseManager.getUser().remSubscribedExp(experiment);
        databaseManager.getDb().collection("users")
                .document(databaseManager.getAndroid_id())
                .update("subscribedExp", FieldValue.arrayRemove(experiment.getName().toLowerCase()));
    }

    public void Owner_endExp(Experiment experiment) {
        experiment.end();
    }

    public void recordTrials(Experiment experiment) {
        String typeSelected = experiment.getType();
        if (typeSelected.equals("count")) {
            transition(TrialActivity.class, R.layout.count_based);
        } else if (typeSelected.equals("binomial")) {
            transition(TrialActivity.class, R.layout.binomial);
        } else if (typeSelected.equals("nonNegative")) {
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


    //region Dead Code
    /******************************************************************************
     * Dead Code --- Dead Code --- Dead Code
     ******************************************************************************/
    /**
     public void addExperiment(Experiment exp, String type) {
     experimentManager.addExp(exp, type);
     }
     */
    //endregion
}
