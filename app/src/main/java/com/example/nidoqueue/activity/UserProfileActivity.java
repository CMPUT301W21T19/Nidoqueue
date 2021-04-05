package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ListView;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AbstractActivity{
    ListView userView;
    ImageButton backButton, homeButton;
    User user;
    static RequestManager requestManager = RequestManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    static Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);

        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);

        user = userControl.getUser();

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
            requestManager.home();
        }
    };

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
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

