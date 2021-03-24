package com.example.nidoqueue.controller;

import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.activity.SignInActivity;
import com.example.nidoqueue.activity.UserProfileAddFragment;
import com.example.nidoqueue.activity.WelcomeActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class UserControl {

    private static UserControl userControl = new UserControl();
    private UserControl(){}

    User user;

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public static UserControl getInstance() {
        return userControl;
    }

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static Database database = Database.getInstance();

    FirebaseFirestore db;




    public void verifyLogin() {
        if(true) {
            requestManager.transition(R.layout.welcome_main, WelcomeActivity.class);
        } else {
//          requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), SignInActivity.class);
        }
    }


    public void signIn() {
        contextManager.getActivity().setDocument("users", database.getAndroid_id(), task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("FireStore", "Document exists!");
                    requestManager.transition(R.layout.welcome_user, SignInActivity.class);
                } else {
                    Log.d("FireStore", "Document does not exists!");
                    Toast.makeText(contextManager.getContext(), "Device is not registered Yet!\nPlease sign up to continue", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d("FireStore", "Failed with: ", task.getException());
            }
        });
    }

    public void signUp() {
        contextManager.getActivity().setDocument("users", database.getAndroid_id(), task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("FireStore", "Document exists!");
                    Toast.makeText(contextManager.getContext(), "Device already registered!\nTry sign in into your account", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("FireStore", "Document does not exists!");
                    new UserProfileAddFragment("", "", "").show(contextManager.getActivity().getSupportFragmentManager(), "Add_User");
                }
            } else {
                Log.d("FireStore", "Failed with: ", task.getException());
            }
        });
    }

    public void signInOptions() {

//        requestManager.transition(R.layout.welcome_user, (AbstractActivity) contextManager.getContext(), UserProfileActivity.class);

    }

    public void setID(User user) {
        this.user = user;
        contextManager.getActivity().setCollection("users", database.getAndroid_id(), task -> {
            if (task.isSuccessful()) {
                boolean id_exist = false;
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    if (documentSnapshot.getString("userName").equals(user.getUserName())) {
                        //new UserProfileAddFragment(user.getUserName(), user.getEmail(), user.getPhoneNumber()).show(getSupportFragmentManager(), "Add_User");
                        Toast.makeText(contextManager.getActivity().getApplicationContext(), "User name already exists.\nPlease try with other user name", Toast.LENGTH_LONG).show();
                        id_exist = true;
                        break;
                    }
                }
                if (!id_exist) {
                    db.collection("users")
                            .document(database.getAndroid_id())
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

    public void setUserDB() {
        database.getDb().collection("users")
                .document(database.getAndroid_id())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            database.readDocument(document);
                        }
                    }
                });
    }
}
