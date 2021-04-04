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
    Button signUp, signIn;
    ArrayAdapter<User> Adapter;
    ArrayList<User> userList;

    // get instances of RequestManager and ContextManager
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        contextManager.setContext(this);

        userList = new ArrayList<>();
        Adapter = new UserProfileContent(this, userList);

        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        signIn.setOnClickListener(SignIn);
        signUp.setOnClickListener(SignUp);
    }
    public FirebaseFirestore getDB() {
        return database.getDb();
    }
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
    public void onOkPressed(User newUser) {
        Adapter.add(newUser);
        requestManager.setUserId(newUser);
        requestManager.transition(R.layout.welcome_user, SignInActivity.class);
    }
}
