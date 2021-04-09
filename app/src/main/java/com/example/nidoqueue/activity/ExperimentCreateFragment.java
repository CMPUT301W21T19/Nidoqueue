package com.example.nidoqueue.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.android.material.checkbox.MaterialCheckBox;

/**
 * Classname:   ExperimentCreateFragment.java
 * Version:     Final
 * Date:        Apr 9th, 2021
 * Purpose:     Fragment to create and publish new Experiments.
 */
public class ExperimentCreateFragment extends DialogFragment {
    private EditText expName_EditText, expDesc_EditText, minTrials_EditText;
    private Spinner region, type;
    private MaterialCheckBox geoLocation;
    private OnFragmentInteractionListener listener;
    private static final ContextManager contextManager = ContextManager.getInstance();

    private String name, desc, minTrials, regionInfo, typeInfo;
    private Boolean geoLocationInfo;

    DatabaseManager databaseManager;

    public ExperimentCreateFragment(String name, String desc, String minTrials, String regionInfo, String typeInfo, Boolean geoLocationInfo, DatabaseManager databaseManager) {
        this.name = name;
        this.desc = desc;
        this.minTrials = minTrials;
        this.regionInfo = regionInfo;
        this.typeInfo = typeInfo;
        this.geoLocationInfo = geoLocationInfo;
        this.databaseManager = databaseManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.exp_create_form, null); //  Sets up the view for the add/edit experiment window

        expName_EditText = view.findViewById(R.id.exp_name);
        expDesc_EditText = view.findViewById(R.id.exp_desc);
        minTrials_EditText = view.findViewById(R.id.minTrials);
        region = view.findViewById(R.id.region);
        type = view.findViewById(R.id.type);
        geoLocation = view.findViewById(R.id.geoLocation);

        ArrayAdapter regionAdapter = ArrayAdapter.createFromResource(getContext(), R.array.countries_array, R.layout.custom_dropdown);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.expType_array, R.layout.custom_dropdown);
        region.setAdapter(regionAdapter);
        type.setAdapter(typeAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Create Experiment")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Publish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveExperimentDetails();
                    }
                }) // New experiment is created with new arguments on the press of the "ok" button.
                .create();
    }

    public void saveExperimentDetails() {
        String expName = expName_EditText.getText().toString();
        String expDesc = expDesc_EditText.getText().toString();
        String regionSelected = region.getSelectedItem().toString();
        int minTrials = Integer.parseInt(minTrials_EditText.getText().toString());
        String typeSelected = type.getSelectedItem().toString();
        Boolean geoLocationChecked = geoLocation.isChecked();

        tryExperiment(expName, expDesc, regionSelected, minTrials, typeSelected, geoLocationChecked, false);
    }


    public interface OnFragmentInteractionListener {
        void onOkPressed(Experiment exp, String type); // The new experiment is passed into this method when the "ok" button is pressed.
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
     *
     * @param expName
     * @param expDesc
     * @param regionSelected
     * @param minTrials
     * @param typeSelected
     * @param geoLocationChecked
     */
    public void tryExperiment(String expName, String expDesc, String regionSelected, int minTrials, String typeSelected, Boolean geoLocationChecked, Boolean isTest) {
        /**
         * Comparisons made to contain the user entered strings into legal values for the scope of this project.
         */
        if (expName.length() > 18 || expName.length() < 5) {
            // Cancels fragment if user enters a name that is under 5 character or over 18 characters in string length.
            errorCode(1, null, null, null, 0, null, null, isTest);
        } else if (expDesc.length() > 80 || expDesc.length() < 1) {
            // Cancels fragment if user enters a description that is under 1 character or over 80 characters in string length.
            errorCode(2, null, null, null, 0, null, null, isTest);
        } else if (regionSelected.equals("None")) {
            // Cancels fragment if type not selected
            errorCode(3, null, null, null, 0, null, null, isTest);
        } else if (minTrials > 20 || minTrials < 1) {
            // Cancels fragment if user enters a minimum # of trials that is under 1 character or over 20.
            errorCode(4, null, null, null, 0, null, null, isTest);
        }else if (typeSelected.equals("")) {
            // Cancels fragment if type not selected
            errorCode(5, null, null, null, 0, null, null, isTest);
        }else {
            // Sends a "0" for an error code, which successfully adds a new experiment through "onOkPressed" or "test case".
            errorCode(0, expName, expDesc, regionSelected, minTrials, typeSelected, geoLocationChecked, isTest);
        }
    }
    /**
     * The error code value is passed through. "0" Indicates no errors, through the fragment or the test case.
     * @param error
     * @param expName
     * @param expDesc
     * @param regionSelected
     * @param minTrials
     * @param typeSelected
     * @param geoLocationChecked
     * @param isTest
     */
    public void errorCode(int error, String expName, String expDesc, String regionSelected, int minTrials, String typeSelected, Boolean geoLocationChecked, Boolean isTest) {
        String message = "";
        switch (error) {
            case 0:
                message = "Experiment created successfully!";
                if (isTest) {
                    displayTestResults(message);
                    System.out.print("Error Code: "+error+"\nExperiment Name: "+expName+"\nExperiment Description: " +expDesc+
                            "\nRegion: "+regionSelected+"\nMin Trials: "+minTrials+"\nType: "+typeSelected+"\nGeoLocation: "+geoLocationChecked);
                }else{
                    if (typeSelected.equals("Count")) {
                        listener.onOkPressed(new ExpCount(databaseManager.getUser(), expName, expDesc, regionSelected, minTrials, geoLocationChecked, true), typeSelected);
                    } else if (typeSelected.equals("Binomial")) {
                        listener.onOkPressed(new ExpBinomial(databaseManager.getUser(), expName, expDesc, regionSelected, minTrials, geoLocationChecked, true), typeSelected);
                    } else if (typeSelected.equals("Non Negative")) {
                        listener.onOkPressed(new ExpNonNegative(databaseManager.getUser(), expName, expDesc, regionSelected, minTrials, geoLocationChecked, true), typeSelected);
                    } else if (typeSelected.equals("Measurement")) {
                        listener.onOkPressed(new ExpMeasurement(databaseManager.getUser(), expName, expDesc, regionSelected, minTrials, geoLocationChecked, true, ""), typeSelected);
                    } else {
                        Toast.makeText(getContext(), "Please select experiment type", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 1:
                message = "Sorry, the experiment name needs to be \nbetween 5 and 18 characters in length";
                if (isTest) {
                    displayTestResults(message);
                }else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                message = "Sorry, the experiment description needs to be \nbetween 1 and 80 characters in length";
                if (isTest) {
                    displayTestResults(message);
                }else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 3:
                message = "Sorry, a region needs to be selected";
                if (isTest) {
                    displayTestResults(message);
                }else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 4:
                message = "Sorry, the minimum amount of trials needs to be \nbetween 1 and 20";
                if (isTest) {
                    displayTestResults(message);
                }else {
                    Toast.makeText(contextManager.getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                break;
            case 5:
                message = "Sorry, a type needs to be selected";
                if (isTest) {
                    displayTestResults(message);
                }else {
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
}
