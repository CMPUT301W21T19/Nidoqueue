package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity{
    ListView userView;
    ImageButton backButton;
    ArrayList<UserProfile> userList;
    ArrayAdapter<UserProfile> Adapter;
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
            userList.add((new UserProfile(Username[i], Email[i], Phone[i])));
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