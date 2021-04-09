package com.example.nidoqueue.controller;

import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.activity.RecoveryFragment;
import com.example.nidoqueue.activity.SearchFragment;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.SignInFragment;
import com.example.nidoqueue.activity.SignUpFragment;
import com.example.nidoqueue.activity.UserProfileActivity;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
/**
 * Classname:   UserControl.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This handles the User Controlled methods throughout the program.
 */
public class UserControl {

    // Singleton pattern
    private static UserControl userControl = new UserControl();

    private UserControl() { }
    public static UserControl getInstance() {
        return userControl;
    }
    // Get instances of other Singleton classes needed
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final RequestManager requestManager = RequestManager.getInstance();
    // private static final ExperimentManager experimentManager = ExperimentManager.getInstance();

    private User user;

    //region setters
    public void setUser(User user) {
        this.user = user;
    }
    //endregion
    //region getters
    public User getUser() {
        return databaseManager.getUser();
    }

    public String getUsername(){
        if(user == null){
            return null;
        }
        return user.getUsername();
    }

    public String getEmail() {
        if(user == null){
            return null;
        }
        return user.getEmail();
    }

    public String getPassword() {
        if(user == null){
            return null;
        }
        return user.getPassword();
    }

    /******************************************************************************
     * User Control Methods
     ******************************************************************************/
    public void profile() {
        requestManager.transition(UserProfileActivity.class);
    }

    public void signIn() {
        new SignInFragment("", "", false).show(contextManager.getActivity().getSupportFragmentManager(), "Sign_In");
        // Check if Android ID exists in User Database
//        requestManager.transition(SignInActivity.class);
    }

    public void signUp() {
        databaseManager.checkDocument("users", databaseManager.getAndroid_id(), exist -> {
            if (exist) {
                Toast.makeText(contextManager.getActivity().getContext(), "Device already registered!\nTry sign in into your account", Toast.LENGTH_LONG).show();
            } else {
                new SignUpFragment("", "", "", "", false).show(contextManager.getActivity().getSupportFragmentManager(), "Add_User");
            }
        });
    }

    public void clickHere() {
        new RecoveryFragment("", false).show(contextManager.getActivity().getSupportFragmentManager(), "Recover_User");
    }

    public void edit() {
        User user = getUser();
        new SignUpFragment(user.getUsername(), user.getEmail(), user.getPassword(), user.getPassword(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Edit_User");
    }

    public void trySignIn(User user) {
        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("username").equals(user.getUsername())) {
                                if (documentSnapshot.getString("password").equals(user.getPassword())) {
                                    //Need to update android_id.
                                    id_exist = true;
                                    databaseManager.setUser(new User(user.getUsername(), user.getEmail(), user.getPassword(), null, null));
                                    requestManager.home();
                                    break;
                                }
                            }
                        }
                        if (!id_exist) {
                            Toast.makeText(contextManager.getContext(), "Account not found. Try Again", Toast.LENGTH_SHORT).show();
                            new SignInFragment(user.getUsername(), user.getPassword(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Sign_In");
                        }
                    }
                });
    }

    public void trySignUp(User newUser) {
        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("username").equals(newUser.getUsername())) {
                                Toast.makeText(contextManager.getContext(), "User name already exists.\nPlease try with other user name", Toast.LENGTH_SHORT).show();
                                id_exist = true;
                                break;
                            }
                        }
                        if (!id_exist) {
                            databaseManager.setDocument("users", databaseManager.getAndroid_id(), newUser, success -> {
                                if (success) {
                                    Log.d("FireStore", "Succeed");
                                    databaseManager.setUser(new User(user.getUsername(), user.getEmail(), user.getPassword(), null, null));
                                    requestManager.home();
                                } else {
                                    //Log.d("FireStore", "Failed with: ", success.getException());
                                }
                            });
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }
    public void tryRecovery(User user) {
        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("email").equals(user.getEmail())) {
                                //Need to update android_id.
                                id_exist = true;
                                databaseManager.setUser(new User(user.getUsername(), user.getEmail(), user.getPassword(), null, null));
                                requestManager.home();
                                break;
                            }
                        }
                        if (!id_exist) {
                            Toast.makeText(contextManager.getContext(), "Account not found. Try Again", Toast.LENGTH_SHORT).show();
                            new RecoveryFragment(user.getEmail(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Recovery");
                        }
                    }
                });
    }
    public void tryEdit(User user) {
        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("username").equals(user.getUsername())) {
                                if (documentSnapshot.getString("password").equals(user.getPassword())) {
                                    //Need to update android_id.
                                    id_exist = true;
                                    databaseManager.setUser(new User(user.getUsername(), user.getEmail(), user.getPassword(), null, null));
                                    requestManager.home();
                                    break;
                                }
                            }
                        }
                        if (!id_exist) {
                            Toast.makeText(contextManager.getContext(), "Account not found. Try Again", Toast.LENGTH_SHORT).show();
                            new SignInFragment(user.getUsername(), user.getPassword(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Sign_In");
                        }
                    }
                });
    }
    public void searchBar(){
        new SearchFragment("", false).show(contextManager.getActivity().getSupportFragmentManager(), "Search_Bar");
    }

    public void init() {
        databaseManager.checkDocument("users", databaseManager.getAndroid_id(), exist -> {
            if (exist) {
                DocumentSnapshot documentSnapshot = databaseManager.getDocument();
                String username = documentSnapshot.getString("username");
                String email = documentSnapshot.getString("email");
                String password = documentSnapshot.getString("password");
                databaseManager.setUser(new User(username, email, password, null, null));
            }
        });
    }

}
