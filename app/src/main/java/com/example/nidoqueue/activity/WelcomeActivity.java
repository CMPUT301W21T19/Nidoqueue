package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.User;

public class WelcomeActivity extends AbstractActivity implements UserProfileAddFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    String Message; // String that is used to display information for each experiment on click.
    boolean doubleBackToExitPressedOnce = false;

    // get instances of RequestManager and ContextManager
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = requestManager.getContextManager();
//    private static final DatabaseManager DATABASE_MANAGER = requestManager.getDatabaseManager();


    private View.OnClickListener SignIn = v -> requestManager.signIn();
    private View.OnClickListener SignUp = v -> requestManager.signUp();


    @Override
    public void onOkPressed(User user) {
        requestManager.setUserId(user);
    }


    // Back button Pressed
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        contextManager.setContext(this);

        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);

        signIn.setOnClickListener(SignIn);
        signUp.setOnClickListener(SignUp);

//        clickHere.setOnClickListener(ClickHere);
    }
}
