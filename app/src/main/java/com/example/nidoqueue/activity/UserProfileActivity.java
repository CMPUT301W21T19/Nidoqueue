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
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity to show the profile of the user.
 */

public class UserProfileActivity extends AbstractActivity implements SignUpFragment.OnFragmentInteractionListener, RecyclerViewClickListener {
    ListView userView;
    ImageButton backButton, homeButton, editButton;
    TextView userName, email;

    // get instances of RequestManager and ContextManager
    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    @Override
    public void recyclerViewListClicked(View v, int position) {
        requestManager.transition(ExperimentActivity.class, position);
    }

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
            finish();
            requestManager.back();
        }
    };
    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.edit();
        }
    };

    @Override
    public void onBackPressed() {
        finish();
        requestManager.back();
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
    /**
     * All the information needed for the user profile is injected on the "ok" press
     * @param user
     */
    @Override
    public void onSignUpOkPressed(User user) {
        requestManager.tryEdit(user);

    }
}
