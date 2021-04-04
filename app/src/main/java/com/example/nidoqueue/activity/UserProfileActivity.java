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
    ImageButton backButton;
    ImageButton homeButton;
    User user;
    static RequestManager requestManager = RequestManager.getInstance();
    static Database database = Database.getInstance();
    static UserControl userControl = UserControl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);

        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);

        user = userControl.getUser();

        //userName.setText(user.getUserName());
        //email.setText(user.getEmail());
        //phoneNumber.setText(user.getPhoneNumber());

        TextView userName = findViewById(R.id.user_title);
        //TextView userName = findViewById(R.id.username_display);
        TextView email = findViewById(R.id.email_display);
        TextView phoneNumber = findViewById(R.id.phone_display);

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

