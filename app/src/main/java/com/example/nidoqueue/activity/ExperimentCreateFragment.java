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
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.google.android.material.checkbox.MaterialCheckBox;

import static android.content.ContentValues.TAG;

/**
 * Classname:   ExperimentCreateFragment.java
 * Version:     Prototype
 * Date:        Apr 9th, 2021
 * Purpose:     Fragment to create and publish new Experiment
 * Issues:      None
 */
public class ExperimentCreateFragment extends DialogFragment {
    private EditText expName_EditText, expDesc_EditText, minTrials_EditText;
    private Spinner region, type;
    private MaterialCheckBox geoLocation;
    private OnFragmentInteractionListener listener;

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
}
