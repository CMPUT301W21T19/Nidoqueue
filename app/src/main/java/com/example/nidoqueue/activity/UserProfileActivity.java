package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.R;
import com.example.nidoqueue.model.DatabaseAlt;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserProfileActivity extends AbstractActivity{
    ListView userView;
    ImageButton backButton, homeButton, editButton;
    ArrayList<User> userList;
    ArrayAdapter<User> Adapter;
    User editRemove;
    String message;
    User user;

    // get instances of RequestManager and ContextManager
    static RequestManager requestManager = RequestManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();
    static UserControl userControl = UserControl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        userView = findViewById(R.id.user_info);
        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);
        editButton = findViewById(R.id.edit_button);

        userView.setOnClickListener(Profile);
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
}

