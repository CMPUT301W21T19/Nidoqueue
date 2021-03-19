package com.example.nidoqueue.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;

public class UserProfileAddFragment extends DialogFragment {
    private EditText userName_EditText, email_EditText, phoneNumber_EditText;
    private String userName, email, phoneNumber;
    private OnFragmentInteractionListener listener;

    public UserProfileAddFragment(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public interface OnFragmentInteractionListener {
        void onOkPressed(User newUser); // The new experiment is passed into this method when the "ok" button is pressed.
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sign_up_form, null); //  Sets up the view for the add/edit experiment window
        userName_EditText = view.findViewById(R.id.username_add);
        email_EditText = view.findViewById(R.id.email_add);
        phoneNumber_EditText = view.findViewById(R.id.phone_add);

        userName_EditText.setText(userName); // Sets the text to the values in the get method called from the experiment class
        email_EditText.setText(email); // The text will not be set if the value represented in the argument is "null"
        phoneNumber_EditText.setText(phoneNumber);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Profile")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    String userName = userName_EditText.getText().toString(); // Allows the user to input text
                    String email = email_EditText.getText().toString();
                    String phoneNumber = phoneNumber_EditText.getText().toString();

                    listener.onOkPressed(new User(userName, email, phoneNumber));
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();

    }

}
