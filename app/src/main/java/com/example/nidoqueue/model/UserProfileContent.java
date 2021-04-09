package com.example.nidoqueue.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.nidoqueue.R;

import java.util.ArrayList;
/**
 * Classname: 	UserProfileContent.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Class for user class that holds the User Profile information for the user.
 */
public class UserProfileContent extends ArrayAdapter<User> {
    private ArrayList<User> profiles;
    private Context context;

    public UserProfileContent(Context context, ArrayList<User> profiles) {
        super(context, 0, profiles);
        this.profiles = profiles;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.user_profile_display, parent, false);
        }
        User pro = profiles.get(position);
        TextView Username = view.findViewById(R.id.username_display);
        TextView Email = view.findViewById(R.id.email_display);
        Username.setText(pro.getUsername()); // Retrieves the info from get methods found in the user class
        Email.setText(pro.getEmail());
        return view;
    }

}