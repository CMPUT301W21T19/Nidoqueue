package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class WelcomeActivity extends AbstractActivity implements UserProfileAddFragment.OnFragmentInteractionListener {
    Button signUp, signIn, clickHere;
    ArrayAdapter<User> Adapter;
    String Message; // String that is used to display information for each experiment on click.
    ArrayList<User> userList;
    DatabaseManager dbManager;
    FirebaseFirestore db;
    String android_id;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        dbManager = (DatabaseManager) getApplicationContext();
        db = dbManager.getDb();
        android_id = dbManager.getAndroid_id();

        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_button);
        clickHere = findViewById(R.id.click_here_button);
        userList = new ArrayList<>();
        Adapter = new UserProfileContent(this, userList);

        signIn.setOnClickListener(v -> signIn());
        signUp.setOnClickListener(v -> signUp());
        clickHere.setOnClickListener(v -> clickHere());
    }

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
    public void onOkPressed(User user) {
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean id_exist = false;
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if (documentSnapshot.getString("userName").equals(user.getUserName())) {
                                //new UserProfileAddFragment(user.getUserName(), user.getEmail(), user.getPhoneNumber()).show(getSupportFragmentManager(), "Add_User");
                                Toast.makeText(getApplicationContext(), "User name already exists.\nPlease try with other user name", Toast.LENGTH_LONG).show();
                                id_exist = true;
                                break;
                            }
                        }
                        if (!id_exist) {
                            db.collection("users")
                                    .document(android_id)
                                    .set(user)
                                    .addOnCompleteListener(action -> {
                                        if (action.isSuccessful()) {
                                            Log.d("FireStore", "Succeed");
                                            signIn();
                                        } else {
                                            Log.d("FireStore", "Failed with: ", action.getException());
                                        }
                                    });
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void signIn() {
        db.collection("users")
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
        db.collection("users")
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
