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
import com.example.nidoqueue.model.Database;
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
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final Database database = Database.getInstance();
    private static final DatabaseAlt databaseAlt = DatabaseAlt.getInstance();
    private static final UserControl userControl = UserControl.getInstance();

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

        //userList = databaseAlt.get_UserDB();
        /**

        for (int i = 0; i < 1; i++) {
            userList.get(i);
        }
         */
        //Adapter = new UserProfileContent(this, userList);
        //userView.setAdapter(Adapter); // This view is setup to display the experiments

        //Username.setText(user.getUsername());
        //email.setText(user.getEmail());

       // TextView usernameT = findViewById(R.id.user_title);
        //TextView username = findViewById(R.id.username_display);
       // TextView email = findViewById(R.id.email_display);

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

