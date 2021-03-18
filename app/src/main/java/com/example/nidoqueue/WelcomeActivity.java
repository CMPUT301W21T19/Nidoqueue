package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements UserProfileAdd.OnFragmentInteractionListener{
    Button signUp, signIn, clickHere;
    ArrayAdapter<UserProfile> Adapter;
    String Message; // String that is used to display information for each experiment on click.
    ArrayList<UserProfile> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);
        userList = new ArrayList<>();
        Adapter = new UserProfileContent(this, userList);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHere();
            }
        });
    }
    public void onOkPressed(UserProfile newUser){
        Adapter.add(newUser);
        signIn();
    }

    public void signIn(){
        /*Add the if statement, userId or no userID */
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void signUp(){
        new UserProfileAdd(null,null,null).show(getSupportFragmentManager(), "Add_User");
    }

    public void clickHere(){
        Message = "Feature will be available in the official release";
        Toast.makeText(this, Message, Toast.LENGTH_LONG).show();
    }

}