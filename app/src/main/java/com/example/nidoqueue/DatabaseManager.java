package com.example.nidoqueue;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseManager extends Application {

    FirebaseFirestore db;
    String android_id;
    User user;

    @Override
    public void onCreate() {
        super.onCreate();
        db  = FirebaseFirestore.getInstance();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        getDb().collection("users")
                .document(getAndroid_id())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("FireStore", "Success");
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            String userName = document.getString("userName");
                            String email = document.getString("email");
                            String phoneNumber = document.getString("phoneNumber");
                            setUser(new User(userName, email, phoneNumber));
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public String getAndroid_id() {
        return android_id;
    }
}
