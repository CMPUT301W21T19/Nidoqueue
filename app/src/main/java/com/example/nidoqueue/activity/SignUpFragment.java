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

public class SignUpFragment extends DialogFragment {
    private EditText userName_EditText, email_EditText, password_EditText, passwordRe_EditText;
    private String username, email, password;
    private OnFragmentInteractionListener listener;
    private static final ContextManager contextManager = ContextManager.getInstance();

    public SignUpFragment(String username, String email, String password) {
        this.username = username;
        this.email = email;
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sign_up_form, null); //  Sets up the view for the add/edit experiment window
        userName_EditText = view.findViewById(R.id.username_add);
        email_EditText = view.findViewById(R.id.email_add);
        password_EditText = view.findViewById(R.id.password_add);
        passwordRe_EditText = view.findViewById(R.id.password_re_add);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Profile")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String username = userName_EditText.getText().toString(); // Allows the user to input text
                        String email = email_EditText.getText().toString();
                        String password = password_EditText.getText().toString();
                        String passwordRe = passwordRe_EditText.getText().toString();
                        if(!password.equals(passwordRe)){
                            Toast.makeText(contextManager.getActivity().getApplicationContext(), "Sorry, the passwords need to match", Toast.LENGTH_LONG).show();
                        }else{
                            listener.onOkPressed(new User(username, email, password, null, null));
                        }

                    }
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();

    }

}
