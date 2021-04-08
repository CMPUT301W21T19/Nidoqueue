package com.example.nidoqueue.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
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
    private Boolean isTest;
    private OnFragmentInteractionListener listener;
    private static final ContextManager contextManager = ContextManager.getInstance();

    public RecoveryFragment(String email, boolean isTest) {
        this.email = email;
        this.isTest = isTest;
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
        email_EditText.setText(email);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Account Recovery")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String email = email_EditText.getText().toString();
                        tryRecovery(email, false);
                    }
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }
    /******************************************************************************
     * Error Catching methods are called.
     ******************************************************************************/
    /**
     *
     * @param email - User entered string is passed through.
     * @param isTest - The value is true if it's a test case.
     */
    public void tryRecovery(String email, Boolean isTest){
        /**
         * Comparisons made to contain the user entered strings into legal values for the scope of this project.
         */
        if(email.length()>34 || email.length()<10){
            // Cancels fragment if user enters a username that is under 1 character or over 18 characters in string length.
            errorCode(1, null, isTest);
        } else{
            // Sends a "0" for an error code, which successfully adds a new user through "onOkPressed" or "test case".
            errorCode(0, email, isTest);
        }
    }
    /**
     *
     * @param error - The error code value is passed through.
     *                "0" Indicates no errors, through the fragment or the test case.
     * @param email - Only passed in if no errors exist.
     * @param isTest - Only passed in if no errors exist.
     */
    public void errorCode(int error, String email, Boolean isTest){
        String message = "";
        switch (error){
            case 0:
                message = "Check your email for a recovery code!";
                if(isTest){
                    displayTestResults(message);
                    //new User(username, email, password, null, null);
                    System.out.print("Email: "+email+"\nResults: ");
                    break;
                }else{
                    //listener.onOkPressed(new User(username, email, password, null, null));
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                }
            case 1:
                message = "Sorry, this email does not exist on the system";
                if(isTest){
                    displayTestResults(message);
                    break;
                }else{
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
    /**
     *
     * @param message - Message that is displayed to the user through the user interface, or the test terminal.
     */
    public void displayTestResults(String message){
        System.out.print("\n"+message+"\n");
    }
}
