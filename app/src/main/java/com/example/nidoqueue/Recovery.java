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

public class Recovery extends DialogFragment {
    private EditText Email;
    private String email;
    private OnFragmentInteractionListener listener;
    public Recovery(String email){
        this.email = email;
    }
    public interface OnFragmentInteractionListener {
        void onOkPressed(User newUser); // The new experiment is passed into this method when the "ok" button is pressed.
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recovery_form, null); //  Sets up the view for the add/edit experiment window
        Email = view.findViewById(R.id.email_recovery);
        Email.setText(email); // The text will not be set if the value represented in the argument is "null"
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Account Recovery")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = Email.getText().toString();
                        //listener.onOkPressed(new UserProfile(email));
                    }}) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }
}
