package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AbstractActivity {
    Button signUp;
    Button signIn;
    Button clickHere;
    ArrayList<UserProfile> userList;

    // initialize RequestManager
    RequestManager requestManager = RequestManager.getInstance();

    ContextManager contextManager = ContextManager.getInstance();

    private View.OnClickListener SignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp();
        }
    };

    private View.OnClickListener SignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signIn();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contextManager.setContext(MainActivity.this);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);
        userList = new ArrayList<>();
        String[] Username = {};
        String[] Email = {};
        String[] Phone = {};
        for (int i = 0; i < Username.length; i++) {
            userList.add((new UserProfile(Username[i], Email[i], Phone[i])));
        }

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
        //Adapter.remove(editRemove); // Only activated if experiment is being edited.
        userList.add(newUser);
    }

    public void signIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void signUp(){
        new AddUser(null,null,null).show(getSupportFragmentManager(), "Add_User");
    }

    public void clickHere(){

    }

}