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

        //userList = databaseAlt.get_UserDB();


        //userName.setText(user.getUserName());
        //email.setText(user.getEmail());
        //phoneNumber.setText(user.getPhoneNumber());

        //TextView userName = findViewById(R.id.user_title);
        //TextView userName = findViewById(R.id.username_display);
        //TextView email = findViewById(R.id.email_display);
        //TextView phoneNumber = findViewById(R.id.phone_display);

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

    public FirebaseFirestore getDB() {
        return null;
    }

    /******************************************************************************
     * Colin --- Saving this code for later. We can use this.
     ******************************************************************************/
    /**
    public userArray(){
        userList = new ArrayList<>();
        String[] Username = {"Username"};
        String[] Email = {"Email"};
        String[] Phone = {"Phone"};
        for (int i = 0; i < Username.length; i++) {
            userList.add((new User(Username[i], Email[i], Phone[i])));
        }
        Adapter = new UserProfileContent(this, userList);
        userView.setAdapter(Adapter); // This view is setup to display the experiments
    }
     */
}

