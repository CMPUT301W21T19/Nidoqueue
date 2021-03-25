package com.example.nidoqueue.controller;

import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.UserProfileAddFragment;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserControl {

    //private static final UserControl userControl = new UserControl();

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

//    public static UserControl getInstance() {
//        return userControl;
//    }

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager;
    DatabaseManager databaseManager;

    UserControl() {
        contextManager = requestManager.getContextManager();
        databaseManager = requestManager.getDatabaseManager();
    }

    public void verifyLogin() {
        if (true) {
            requestManager.transition(R.layout.welcome_main, WelcomeActivity.class);
        } else {
//          requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), SignInActivity.class);
        }
    }


    public void signIn() {
        databaseManager.checkDocument("users", databaseManager.android_id, exist -> {
            if (exist) {
                requestManager.transition(R.layout.welcome_user, SignInActivity.class);
            } else {
                Toast.makeText(contextManager.getContext(), "Device is not registered Yet!\nPlease sign up to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUp() {
        databaseManager.checkDocument("users", databaseManager.android_id, exist -> {
            if (exist) {
                Toast.makeText(contextManager.getContext(), "Device already registered!\nTry sign in into your account", Toast.LENGTH_SHORT).show();
            } else {
                new UserProfileAddFragment("", "", "").show(contextManager.getActivity().getSupportFragmentManager(), "Add_User");
            }
        });
    }

    public void signInOptions() {

//        requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), UserProfileActivity.class);

    }

    public void setID(User user) {
        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("userName").equals(user.getUserName())) {
                                Toast.makeText(contextManager.getContext(), "User name already exists.\nPlease try with other user name", Toast.LENGTH_SHORT).show();
                                id_exist = true;
                                break;
                            }
                        }
                        if (!id_exist) {
                            databaseManager.setDocument("users", databaseManager.android_id, user, success -> {
                                if (success) {
                                    Log.d("FireStore", "Succeed");
                                    signIn();
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
}
