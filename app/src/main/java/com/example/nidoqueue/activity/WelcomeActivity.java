package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   WelcomeActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Functions as title screen for users who have just downloaded the app.
 *              They have the option to sign in with an existing account, sign up with a new account
 *              or recover their account through email.
 */

public class WelcomeActivity extends AbstractActivity implements SignUpFragment.OnFragmentInteractionListener, SignInFragment.OnFragmentInteractionListener, RecoveryFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    ArrayList<User> userList;
    User username, email, password;

    // get instances of RequestManager and ContextManager
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();
    private static final UserControl userControl = UserControl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        contextManager.setContext(this);

        userList = new ArrayList<>();
        Adapter = new UserProfileContent(this, userList);

        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);

        signIn.setOnClickListener(SignIn);
        signUp.setOnClickListener(SignUp);
        clickHere.setOnClickListener(ClickHere);
    }
    public FirebaseFirestore getDB() {
        return database.getDb();
    }
    private View.OnClickListener SignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signIn();
        }
    };
    private View.OnClickListener SignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp();
        }
    };
    private View.OnClickListener ClickHere = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.clickHere();
        }
    };
    /**
     * All the information needed for the user profile is injected on the "ok" press
     * @param newUser
     */
    @Override
    public void onSignUpOkPressed(User newUser) {
        requestManager.trySignUp(newUser);
    }
    /**
     * All the information needed to sign in is injected on the "ok" press
     * @param user
     */
    @Override
    public void onSignInOkPressed(User user) {
        requestManager.trySignIn(user);
    }
    /**
     * All the information needed for the account recovery is injected on the "ok" press
     * @param user
     */
    @Override
    public void onRecoveryOkPressed(User user) {
        requestManager.tryRecovery(user);
    }
}
