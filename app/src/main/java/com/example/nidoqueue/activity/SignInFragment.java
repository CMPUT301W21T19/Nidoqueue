package com.example.nidoqueue.activity;

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

import com.example.nidoqueue.R;
import com.example.nidoqueue.model.User;
/**
 * Classname:   Recovery.java
 * Version:     Prototype
 * Date:        April 9th, 2021
 * Purpose:     Fragment window that handles the Account Recovery for the user.
 */
public class SignInFragment extends DialogFragment {
    private EditText username_EditText, password_EditText;
    private String username, password;
    private SignInFragment.OnFragmentInteractionListener listener;

    public SignInFragment(String username, String password) {
        this.username = username;
        this.password = password;
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sign_in_form, null); //  Sets up the view for the add/edit experiment window
        username_EditText = view.findViewById(R.id.username_signIn);
        password_EditText = view.findViewById(R.id.password_signIn);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Sign In")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String username = username_EditText.getText().toString();
                        String password = password_EditText.getText().toString();
                        listener.onOkPressed(new User(username, null, password, null, null));
                    }
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }
}
