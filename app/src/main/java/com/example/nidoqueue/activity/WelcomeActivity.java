package com.example.nidoqueue.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseAlt;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   WelcomeActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Functions as an automatic transition over to the either the Welcome screen or the title screen (Sign up, Sign In)
 */

public class WelcomeActivity extends AbstractActivity implements SignUpFragment.OnFragmentInteractionListener, SignInFragment.OnFragmentInteractionListener, RecoveryFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    ArrayList<User> userList;
    User username, email, password;
    User currentUser = null;

    // get instances of RequestManager and ContextManager
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final DatabaseAlt databaseAlt = DatabaseAlt.getInstance();
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

        username = userControl.getUser();
        password = userControl.getPassword();
        email = userControl.getEmail();
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
    public void onOkPressed(User newUser) {
        Adapter.add(newUser);
        requestManager.setUserId(newUser);
        requestManager.transition(SignInActivity.class);
        if(newUser.getUsername()==null){
            if(newUser.getEmail().equals(email)){
                String Message = "Username: "+username+"\nPassword: "+password;
                Toast.makeText(contextManager.getActivity().getApplicationContext(), Message, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(contextManager.getActivity().getApplicationContext(), "Sorry, this email does not exist in the system.", Toast.LENGTH_LONG).show();
            }
        }else if(newUser.getEmail()==null){
            if(newUser.getUsername().equals(username) && newUser.getPassword().equals(email)){
                requestManager.transition(SignInActivity.class);
            }else{
                Toast.makeText(contextManager.getActivity().getApplicationContext(), "Sorry, the username or password is incorrect.", Toast.LENGTH_LONG).show();
            }
        }else{
            currentUser = newUser;
            databaseAlt.addUserDB(newUser);
            requestManager.transition(SignInActivity.class);
        }
    }
}
