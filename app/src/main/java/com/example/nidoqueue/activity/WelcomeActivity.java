package com.example.nidoqueue.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.UserProfileContent;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class WelcomeActivity extends AbstractActivity implements UserProfileAddFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    String Message; // String that is used to display information for each experiment on click.
    ArrayList<User> userList;
    String android_id;
    // get instances of RequestManager and ContextManager
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final Database database = Database.getInstance();
    boolean doubleBackToExitPressedOnce = false;


    private View.OnClickListener SignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signIn();
        }
    };

    private View.OnClickListener SignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signUp();
        }
    };



    @Override
    public void onOkPressed(User user) {
        requestManager.setUserId(user);
    }
    @Override

    // Back button
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        contextManager.setContext(this);
        android_id = database.getAndroid_id();

        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);
        userList = new ArrayList<>();
        Adapter = new UserProfileContent(this, userList);

        signIn.setOnClickListener(SignIn);
        signUp.setOnClickListener(SignUp);
//        clickHere.setOnClickListener(ClickHere);


    }
    public FirebaseFirestore getDB() {
        return database.getDb();
    }

}
