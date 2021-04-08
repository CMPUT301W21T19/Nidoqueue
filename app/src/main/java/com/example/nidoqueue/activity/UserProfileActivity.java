package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
/**
 * Classname:   UserProfileActivity.java
 * Version:     Prototype
 * Date:        April 9th, 2021
 * Purpose:     Activity to show profile of user
 */
public class UserProfileActivity extends AbstractActivity implements SignUpFragment.OnFragmentInteractionListener{
    ListView userView;
    ImageButton backButton, homeButton, editButton;
    TextView userName, email;
    ArrayList<User> userList;
    ArrayAdapter<User> Adapter;
    User editRemove;
    String message;
    User user;

    // get instances of RequestManager and ContextManager
    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();
    static UserControl userControl = UserControl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        contextManager.setContext(this);

        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);
        editButton = findViewById(R.id.edit_button);

        userName = findViewById(R.id.user_title);
        email = findViewById(R.id.email_display);

        userName.setText(databaseManager.getUser().getUsername());
        email.setText(databaseManager.getUser().getEmail());

        //userView.setOnClickListener(Profile);
        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);
        editButton.setOnClickListener(Edit);
    }
    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.home();
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.back();
        }
    };
    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.edit();
        }
    };
    private View.OnClickListener Profile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.select();
        }
    };
    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }

    @Override
    public void onSignUpOkPressed(User newUser) {

    }
}

