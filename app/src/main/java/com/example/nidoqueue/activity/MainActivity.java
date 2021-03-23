package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;

/**
 * Classname:   MainActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Functions as an automatic transition over to the either the Welcome screen or the title screen (Sign up, Sign In, Recover Account)
 * Issues:      No issues, however this needs to be tested.
 */

public class MainActivity extends AbstractActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextManager.setContext(MainActivity.this);
        setContentView(R.layout.activity_main);

        welcome();
    }

    public void welcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}

// package com.example.nidoqueue;

// import androidx.appcompat.app.AppCompatActivity;
// import android.content.Intent;
// import android.os.Bundle;
// import android.view.View;
// import android.widget.Button;
// import android.widget.Toast;

// import java.util.ArrayList;

// import static com.example.nidoqueue.controller.UserControl.contextManager;

// //public class MainActivity extends AbstractActivity {
// //    ArrayList<UserProfile> userList;
// //
// //    // initialize RequestManager
// //    RequestManager requestManager = RequestManager.getInstance();
// //
// //    ContextManager contextManager = ContextManager.getInstance();
// //
// //    private View.OnClickListener SignUp = new View.OnClickListener() {
// //        @Override
// //        public void onClick(View v) {
// //            requestManager.signUp();
// //        }
// //    };
// //
// //    private View.OnClickListener SignIn = new View.OnClickListener() {
// //        @Override
// //        public void onClick(View v) {
// //            requestManager.signIn();
// //        }
// //    };
// //}

// public class MainActivity extends AppCompatActivity {

//     Button signUp;
//     Button signIn;
//     Button clickHere;

//     @Override
//     protected void onCreate(Bundle savedInstanceState){
//         super.onCreate(savedInstanceState);
//         //contextManager.setContext(MainActivity.this);
//         setContentView(R.layout.activity_main);

//         signIn = findViewById(R.id.sign_in_button);
//         signUp = findViewById(R.id.sign_up_button);
//         clickHere = findViewById(R.id.click_here_button);

//         signUp.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                signUp();
//             }
//         });
//         clickHere.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 clickHere();
//             }
//         });
//     }
//     public void onOkPressed(UserProfile newUser){
//         //Adapter.remove(editRemove); // Only activated if experiment is being edited.
//         //userList.add(newUser);
//     }

//     public void signIn(){
//         Intent intent = new Intent(this, SignInActivity.class);
//         startActivity(intent);
//     }

//     public void signUp(){
//         new AddUser(null,null,null).show(getSupportFragmentManager(), "Add_User");
//     }

//     public void clickHere(){
//         Intent intent = getIntent();
//     }

//     public void signIn(View view){
//         setContentView(R.layout.welcome_user);
//         Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//         //startActivity(intent);
//     }

//     public void signUp(View view){
//         setContentView(R.layout.sign_up);
//         Intent intent = new Intent(this, SignUpAcitvity.class);
//         //startActivity(intent);
//     }

//     public void clickHere(View view){
//         setContentView(R.layout.account_recovery);
//         Intent intent = new Intent(MainActivity.this, RecoveryActivity.class);
//         //startActivity(intent);
//     }
// }
