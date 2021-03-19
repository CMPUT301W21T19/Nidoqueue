package com.example.nidoqueue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/**
 * This method of setting up the custom list was used from the CMPUT301 labs with credit going to the TA's.
 */
public class UserProfileContent extends ArrayAdapter<User> {
    private ArrayList<User> profiles;
    private Context context;
    public UserProfileContent(Context context, ArrayList<User> profiles){
        super(context, 0, profiles);
        this.profiles = profiles;
        this.context = context;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.user_profile_display,parent,false);
        }
        User user = profiles.get(position);
        TextView Username = view.findViewById(R.id.username_display);
        TextView Email = view.findViewById(R.id.email_display);
        TextView Phone = view.findViewById(R.id.phone_display);
        Username.setText(user.getUserName());
        Email.setText(user.getEmail());
        Phone.setText(user.getPhoneNumber());
        return view;
    }

}