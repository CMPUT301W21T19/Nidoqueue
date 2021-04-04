package com.example.nidoqueue.controller;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.R;
import com.example.nidoqueue.activity.UserProfileActivity;
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

    FirebaseFirestore db;
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


    public void signIn(){
        requestManager.transition(R.layout.welcome_user, SignInActivity.class);
    }
    public void profile(){
        requestManager.transition(R.layout.user_profile, UserProfileActivity.class);
    }

    public void signUp(){
        new UserProfileAddFragment("", "", "").show(contextManager.getActivity().getSupportFragmentManager(), "Add_User");
    }

    public void setID(User user) {
        this.user = user;
        //setID_Firebase(user);
    }
    public void setID_Firebase(User user){
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
}
