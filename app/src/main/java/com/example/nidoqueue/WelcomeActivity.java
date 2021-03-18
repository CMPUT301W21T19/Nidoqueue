package com.example.nidoqueue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements UserProfileAddFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    String Message; // String that is used to display information for each experiment on click.
    ArrayList<User> userList;
    private String android_id;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

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

    public void onOkPressed(User user) {
        //Adapter.add(newUser);

        DatabaseManager.db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if(documentSnapshot.getString("userName").equals(user.getUserName())) {
                                    new UserProfileAddFragment(user.getUserName(), user.getEmail(), user.getPhoneNumber()).show(getSupportFragmentManager(), "Add_User");
                                    Toast.makeText(getApplicationContext(), "User name already exists.\nPlease try with other user name", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                        } else {
                            DatabaseManager.db.collection("users")
                                    .document(android_id)
                                    .set(user)
                                    .addOnCompleteListener(action -> {
                                        if (action.isSuccessful()) {
                                            Log.d("FireStore", "Succeed");
                                            signIn();
                                        } else {
                                            Log.d("FireStore", "Failed with: ", task.getException());
                                        }
                                    });
                        }
                    }
                });
    }

    public void signIn() {
        DatabaseManager.db.collection("users")
                .document(android_id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("FireStore", "Document exists!");
                            Intent intent = new Intent(this, SignInActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("FireStore", "Document does not exists!");
                            Toast.makeText(getApplicationContext(), "Device is not registered Yet!\nPlease sign up to continue", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void signUp() {
        DatabaseManager.db.collection("users")
                .document(android_id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("FireStore", "Document exists!");
                            Toast.makeText(getApplicationContext(), "Device already registered!\nTry sign in into your account", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("FireStore", "Document does not exists!");
                            new UserProfileAddFragment("", "", "").show(getSupportFragmentManager(), "Add_User");
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void clickHere() {
        Message = "Feature will be available in the official release";
        Toast.makeText(this, Message, Toast.LENGTH_LONG).show();
    }

}