package com.example.nidoqueue.controller;

import android.util.Log;
import android.widget.Toast;

import com.example.nidoqueue.activity.ExperimentCreateFragment;
import com.example.nidoqueue.activity.SignUpFragment;
import com.example.nidoqueue.model.DataCalc;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
/**
 * Classname:   ExperimentManager.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This handles the experiments throughout the program.
 */
public class ExperimentManager {
    private static final ExperimentManager experimentManager = new ExperimentManager();
    public static ExperimentManager getInstance() {
        return experimentManager;
    }
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final UserControl userControl = UserControl.getInstance();
    String android_id;
    DataCalc calc;
    Experiment currentExperiment;

    public void setCurrentExperiment(Experiment experiment) {
        this.currentExperiment = experiment;
        this.calc = new DataCalc(experiment);
    }

    public Experiment getCurrentExperiment() {
        return (this.currentExperiment);
    }


    public DataCalc getCurrentCalc() {
        return (this.calc);
    }



    public void addExp(Experiment exp, String type) {

        FirebaseFirestore db = contextManager.getActivity().getDB();
        User user = userControl.getUser();

        Log.d("Test", "Clicked");

        db.collection("experiments")
                .document(exp.getName().toLowerCase())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("FireStore", "Document exists!");
                            Toast.makeText(contextManager.getActivity().getApplicationContext(), "Name already exists!\nTry use other name", Toast.LENGTH_LONG).show();
                        } else {
                            user.createExp(exp);
                            db.collection("experiments")
                                    .document(exp.getName().toLowerCase())
                                    .set(exp)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Map<String, String> ownerId = new HashMap<>();
                                            ownerId.put("owner", android_id);
                                            db.collection("experiments")
                                                    .document(exp.getName().toLowerCase())
                                                    .set(ownerId, SetOptions.merge());

                                            db.collection("users")
                                                    .document(android_id)
                                                    .update("createdExp", FieldValue.arrayUnion(exp));
                                        }
                                    });
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }
}
