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
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;

/**
 * Classname:   SearchFragment.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Fragment that handles the search process.
 */
public class SearchFragment extends DialogFragment {
    private EditText search_EditText;
    private String search;
    private Boolean isTest;
    private OnFragmentInteractionListener listener;
    private static final ContextManager contextManager = ContextManager.getInstance();

    public SearchFragment(String search, Boolean isTest) {
        this.search = search;
        this.isTest = isTest;
    }

    public interface OnFragmentInteractionListener {
        void onOkPressed(String keyword); // The new experiment is passed into this method when the "ok" button is pressed.
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.search_form, null); //  Sets up the view for the add/edit experiment window
        search_EditText = view.findViewById(R.id.search_bar);
        search_EditText.setText(search);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Search")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String search = search_EditText.getText().toString(); // Allows the user to input text
                        listener.onOkPressed(search);
                        trySearch(search, false);
                    }
                }) // New user is created with new arguments on the press of the "ok" button.
                .create();

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(this, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }


    /******************************************************************************
     * Error Catching methods are called.
     ******************************************************************************/
    /**
     * @param search - User entered string is passed through.
     * @param isTest - The value is true if it's a test case.
     */
    public void trySearch(String search, Boolean isTest) {
        /**
         * Comparisons made to contain the user entered strings into legal values for the scope of this project.
         */
        if (search.length() > 18 || search.length() < 1) {
            // Cancels fragment if user enters a username that is under 1 character or over 18 characters in string length.
            errorCode(1, null, isTest);
        } else {
            // Sends a "0" for an error code, which successfully adds a new user through "onOkPressed" or "test case".
            errorCode(0, search, isTest);
        }
    }

    /**
     * @param error  - The error code value is passed through.
     *               "0" Indicates no errors, through the fragment or the test case.
     * @param search - Only passed in if no errors exist.
     * @param isTest - Only passed in if no errors exist.
     */
    public void errorCode(int error, String search, Boolean isTest) {
        String message = "";
        switch (error) {
            case 0:
                message = "Search results were successful!";
                if (isTest) {
                    displayTestResults(message);
                    //new User(username, email, password, null, null);
                    System.out.print("Search: " + search + "\nResults: ");
                    break;
                } else {
                    //listener.onOkPressed(new User(username, email, password, null, null));
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                }
            case 1:
                message = "Sorry, no results were found";
                if (isTest) {
                    displayTestResults(message);
                    break;
                } else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }

    /**
     * @param message - Message that is displayed to the user through the user interface, or the test terminal.
     */
    public void displayTestResults(String message) {
        System.out.print("\n" + message + "\n");
    }

}
