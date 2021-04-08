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
public class RecoveryFragment extends DialogFragment {
    private EditText email_EditText;
    private String email;
    private OnFragmentInteractionListener listener;

    public RecoveryFragment(String email) {
        this.email = email;
    }
    public interface OnFragmentInteractionListener {
        void onRecoveryOkPressed(User newUser); // The new experiment is passed into this method when the "ok" button is pressed.
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recovery_form, null); //  Sets up the view for the add/edit experiment window
        email_EditText = view.findViewById(R.id.email_recovery);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Account Recovery")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String email = email_EditText.getText().toString();
                        listener.onRecoveryOkPressed(new User(null, email, null, null, null));
                    }
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }
}
