package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;

public class UserProfileActivity extends AppCompatActivity {
//    ListView userView;
    ImageButton backButton;
//    ArrayList<User> userList;
//    ArrayAdapter<User> Adapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        backButton = findViewById(R.id.back_button5);
//        userView = findViewById(R.id.user_info);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

//        userList = new ArrayList<>();
//        String[] Username = {"Username"};
//        String[] Email = {"Email"};
//        String[] Phone = {"Phone"};
//        for (int i = 0; i < Username.length; i++) {
//            userList.add((new User(Username[i], Email[i], Phone[i])));
//        }
//        Adapter = new UserProfileContent(this, userList);
//        userView.setAdapter(Adapter); // This view is setup to display the experiments

        TextView userName = findViewById(R.id.user_title);
        //TextView userName = findViewById(R.id.username_display);
        TextView email = findViewById(R.id.email_display);
        TextView phoneNumber = findViewById(R.id.phone_display);

        DatabaseManager dbManager = (DatabaseManager)getApplicationContext();

        dbManager.getDb().collection("users")
                .document(dbManager.getAndroid_id())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("FireStore", "Success");
                        DocumentSnapshot document = task.getResult();
                        if(document!=null) {
                            user = document.toObject(User.class);
                            userName.setText(user.getUserName());
                            email.setText(user.getEmail());
                            phoneNumber.setText(user.getPhoneNumber());
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });

    }

    public void back() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void profile() {


    }

}