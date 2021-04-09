package com.example.nidoqueue.controller;

import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.activity.RecoveryFragment;
import com.example.nidoqueue.activity.SearchFragment;
import com.example.nidoqueue.activity.SignInFragment;
import com.example.nidoqueue.activity.SignUpFragment;
import com.example.nidoqueue.activity.UserProfileActivity;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserControl {
    private static UserControl userControl = new UserControl();

    private UserControl() {
    }

    User user, password, email;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return databaseManager.getUser();
    }

    public User getEmail() {
        return email;
    }

    public User getPassword() {
        return password;
    }

    public static UserControl getInstance() {
        return userControl;
    }

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    /******************************************************************************
     * User Control Methods
     ******************************************************************************/
    public void profile() {
        requestManager.transition(UserProfileActivity.class);
    }
    public void signIn(){
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
    public void clickHere(){
        new RecoveryFragment("", false).show(contextManager.getActivity().getSupportFragmentManager(), "Recover_User");
    }

    public void edit() {
        User user = getUser();
        new SignUpFragment(user.getUsername(), user.getEmail(), user.getPassword(), user.getPassword(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Edit_User");
    }

    public void select() {
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
    public void searchBar(){
        new SearchFragment("", false).show(contextManager.getActivity().getSupportFragmentManager(), "Search_Bar");
    }

    public void init() {
        // Set user
        String username = "NameNameName";
        String email = "EmailEmailEmail";
        String password = "9994445555";
        databaseManager.setUser(new User(username, email, password, null, null));
    }
}
