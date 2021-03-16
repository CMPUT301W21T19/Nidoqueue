package com.example.nidoqueue;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
    }

    public void signIn(View view){
        setContentView(R.layout.welcome_user);
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        //startActivity(intent);
    }

    public void signUp(View view){
        setContentView(R.layout.sign_up);
        Intent intent = new Intent(this, SignUpAcitvity.class);
        //startActivity(intent);
    }

    public void clickHere(View view){
        setContentView(R.layout.account_recovery);
        Intent intent = new Intent(MainActivity.this, RecoveryActivity.class);
        //startActivity(intent);
    }

}