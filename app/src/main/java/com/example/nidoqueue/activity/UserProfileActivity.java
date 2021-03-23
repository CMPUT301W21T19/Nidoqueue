package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ListView;

import com.example.nidoqueue.controller.DatabaseManager;
import com.example.nidoqueue.controller.RequestManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;

import java.util.ArrayList;

public class UserProfileActivity extends AbstractActivity{
    ListView userView;
    ImageButton backButton;
    ImageButton homeButton;

    DatabaseManager dbManager;
    User user;

    static RequestManager requestManager = RequestManager.getInstance();

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Home();
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.Home();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        homeButton = findViewById(R.id.home_button2);
        backButton = findViewById(R.id.back_button5);

        backButton.setOnClickListener(Back);
        homeButton.setOnClickListener(Home);

        dbManager = (DatabaseManager) getApplicationContext();
        user = dbManager.getUser();


//        userList = new ArrayList<>();
//        String[] Username = {"Username"};
//        String[] Email = {"Email"};
//        String[] Phone = {"Phone"};
//        for (int i = 0; i < Username.length; i++) {
//            userList.add((new User(Username[i], Email[i], Phone[i])));
//        }
//        Adapter = new UserProfileContent(this, userList);
//        userView.setAdapter(Adapter); // This view is setup to display the experiments

        TextView userName = findViewById(R.id.user_title);
        //TextView userName = findViewById(R.id.username_display);
        TextView email = findViewById(R.id.email_display);
        TextView phoneNumber = findViewById(R.id.phone_display);

        //DatabaseManager dbManager = (DatabaseManager) getApplicationContext();

//        dbManager.getDb().collection("users")
//                .document(dbManager.getAndroid_id())
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Log.d("FireStore", "Success");
//                        DocumentSnapshot document = task.getResult();
//                        if (document != null) {
//                            user = document.toObject(User.class);
//                            userName.setText(user.getUserName());
//                            email.setText(user.getEmail());
//                            phoneNumber.setText(user.getPhoneNumber());
//                        }
//                    } else {
//                        Log.d("FireStore", "Failed with: ", task.getException());
//                    }
//                });

        userName.setText(user.getUserName());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
    }

    public void back() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void profile() {
    }

}
