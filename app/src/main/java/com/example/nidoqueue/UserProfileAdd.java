package com.example.nidoqueue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UserProfileAdd extends DialogFragment {
    private EditText Username, Email, Phone;
    private String username, email, phone;
    private OnFragmentInteractionListener listener;
    public UserProfileAdd(String username, String email, String phone){
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
    public interface OnFragmentInteractionListener {
        void onOkPressed(UserProfile newUser); // The new experiment is passed into this method when the "ok" button is pressed.
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement onFragmentInteractionListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sign_up_form, null); //  Sets up the view for the add/edit experiment window
        Username = view.findViewById(R.id.username_add);
        Email = view.findViewById(R.id.email_add);
        Phone = view.findViewById(R.id.phone_add);
        Username.setText(username); // Sets the text to the values in the get method called from the experiment class
        Email.setText(email); // The text will not be set if the value represented in the argument is "null"
        Phone.setText(phone);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Profile")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = Username.getText().toString(); // Allows the user to input text
                        String email = Email.getText().toString();
                        String phone = Phone.getText().toString();
                        listener.onOkPressed(new UserProfile(username,email,phone));
                    }}) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }
}
