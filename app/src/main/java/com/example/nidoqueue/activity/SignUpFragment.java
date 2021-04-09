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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.User;

/**
 * Classname:   SignUpFragment.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Fragment that handles the sign up process.
 */
public class SignUpFragment extends DialogFragment {
    private EditText userName_EditText, email_EditText, password_EditText, passwordRe_EditText;
    private String username, email, password, passwordRe;
    private Boolean isTest;
    private OnFragmentInteractionListener listener;
    private static final ContextManager contextManager = ContextManager.getInstance();

    public SignUpFragment(String username, String email, String password, String passwordRe, Boolean isTest) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordRe = passwordRe;
        this.isTest = isTest;
    }

    public interface OnFragmentInteractionListener {
        void onSignUpOkPressed(User newUser); // The new experiment is passed into this method when the "ok" button is pressed.
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
        password_EditText = view.findViewById(R.id.password_add);
        passwordRe_EditText = view.findViewById(R.id.password_re_add);
        userName_EditText.setText(username);
        email_EditText.setText(email);
        password_EditText.setText(password);
        passwordRe_EditText.setText(passwordRe);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Profile")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String username = userName_EditText.getText().toString(); // Allows the user to input text
                        String email = email_EditText.getText().toString();
                        String password = password_EditText.getText().toString();
                        String passwordRe = passwordRe_EditText.getText().toString();
                        trySignUp(username, email, password, passwordRe, false);
                    }
                }) // New user is created with new arguments on the press of the "ok" button.
                .create();

    }
    /******************************************************************************
     * Error Catching methods are called.
     ******************************************************************************/
    /**
     * @param username   - User entered string is passed through.
     * @param email      - User entered string is passed through.
     * @param password   - User entered string is passed through.
     * @param passwordRe - User entered string is passed through.
     * @param isTest     - The value is true if it's a test case.
     */
    public void trySignUp(String username, String email, String password, String passwordRe, Boolean isTest) {
        /**
         * Comparisons made to contain the user entered strings into legal values for the scope of this project.
         */
        if (username.length() > 18 || username.length() < 8) {
            // Cancels fragment if user enters a username that is under 8 character or over 18 characters in string length.
            errorCode(1, null, null, null, isTest);
        } else if (email.length() > 34 || email.length() < 10) {
            // Cancels fragment if user enters an email that is under 10 character or over 34 characters in string length.
            errorCode(2, null, null, null, isTest);
        } else if (password.length() > 18 || password.length() < 8) {
            // Cancels fragment if user enters a password that is under 8 character or over 18 characters in string length.
            errorCode(3, null, null, null, isTest);
        } else if (!password.equals(passwordRe)) {
            // Cancels fragment if passwords do not match
            errorCode(4, null, null, null, isTest);
        } else {
            // Sends a "0" for an error code, which successfully adds a new user through "onOkPressed" or "test case".
            errorCode(0, username, email, password, isTest);
        }
    }

    /**
     * @param error    - The error code value is passed through.
     *                 "0" Indicates no errors, through the fragment or the test case.
     * @param username - Only passed in if no errors exist.
     * @param email    - Only passed in if no errors exist.
     * @param password - Only passed in if no errors exist.
     */

    public void errorCode(int error, String username, String email, String password, Boolean isTest) {
        String message = "";
        switch (error) {
            case 0:
                message = "Success!";
                if (isTest) {
                    displayTestResults(message);
                    System.out.print("Username: " + username + "\nEmail: " + email + "\nPassword: " + password);
                } else {
                    listener.onSignUpOkPressed(new User(username, email, password, null, null));
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                message = "Sorry, the username needs to be \nbetween 8 and 18 characters in length";
                if (isTest) {
                    displayTestResults(message);
                } else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                message = "Sorry, the email needs to be \nbetween 10 and 34 characters in length";
                if (isTest) {
                    displayTestResults(message);
                } else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 3:
                message = "Sorry, the password needs to be \nbetween 8 and 18 characters in length";
                if (isTest) {
                    displayTestResults(message);
                } else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                message = "Sorry, the passwords need to match";
                if (isTest) {
                    displayTestResults(message);
                } else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /**
     * @param message - Message that is displayed to the user through the user interface, or the test terminal.
     */
    public void displayTestResults(String message) {
        System.out.print("\n" + message + "\n");
    }

//    @Override
//    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
//        super.show(manager, tag);
//    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(this, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
