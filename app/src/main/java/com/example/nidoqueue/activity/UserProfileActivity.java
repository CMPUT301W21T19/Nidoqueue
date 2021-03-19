package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;

import java.util.ArrayList;

public class UserProfileActivity extends AbstractActivity{
    ListView userView;
    ImageButton backButton;
    ArrayList<User> userList;
    ArrayAdapter<User> Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        backButton = findViewById(R.id.back_button5);
        userView = findViewById(R.id.user_info);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
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
    public void back(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void profile(){


    }

}