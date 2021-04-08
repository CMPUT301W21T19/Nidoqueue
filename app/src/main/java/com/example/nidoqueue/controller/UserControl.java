package com.example.nidoqueue.controller;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.RecoveryFragment;
import com.example.nidoqueue.activity.SignInFragment;
import com.example.nidoqueue.activity.UserProfileActivity;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.SignUpFragment;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserControl {
    private static UserControl userControl = new UserControl();
    private UserControl(){}

    FirebaseFirestore db;
    User user, password, email;

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
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
    public void profile(){
        requestManager.transition(UserProfileActivity.class);
    }
    public void signIn(){
        new SignInFragment("", "").show(contextManager.getActivity().getSupportFragmentManager(), "Sign_In");
    }
    public void signUp(){
        new SignUpFragment("", "", "", "", false).show(contextManager.getActivity().getSupportFragmentManager(), "Add_User");
    }
    public void clickHere(){
        new RecoveryFragment("").show(contextManager.getActivity().getSupportFragmentManager(), "Recover_User");
    }
    public void edit(){
        new SignUpFragment(user.getUsername(), user.getEmail(), user.getPassword(), user.getPassword(), false).show(contextManager.getActivity().getSupportFragmentManager(), "Edit_User");
    }
    public void select(){

    }
}
