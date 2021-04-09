package com.example.nidoqueue.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.ExperimentManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.controller.documentCallBack;
import com.example.nidoqueue.controller.documentSuccessCallBack;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   DatabaseManager.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Sets up the Firestore which handles our database and retrieves user information.
 */
public class DatabaseManager {

    // Singleton Pattern
    private static final DatabaseManager databaseManager = new DatabaseManager();

    @SuppressLint("HardwareIds")
    private DatabaseManager() {
        db = FirebaseFirestore.getInstance();
    }
    public static DatabaseManager getInstance() {
        return databaseManager;
    }
    // Get instances of other Singleton classes needed
    //    private static final ContextManager contextManager = ContextManager.getInstance();
    //    private static final RequestManager requestManager = RequestManager.getInstance();
    //    private static final ExperimentManager experimentManager = ExperimentManager.getInstance();
    //    private static final UserControl userControl = UserControl.getInstance();

    FirebaseFirestore db;
    DocumentSnapshot document;
    String android_id;
    User user;

    ArrayList<Experiment> experiments = new ArrayList<>();
    ArrayList<Experiment> targetExps = new ArrayList<>();
    ArrayList<Question> targetQuestions = new ArrayList<>();
    ArrayList<Experiment> createdExps = new ArrayList<>();
    ArrayList<Experiment> subscribedExps = new ArrayList<Experiment>();


    public void setUser(User user) {
        this.user = user;
    }

    /**
     * public void updateUser() {
     * getDb().collection("users")
     * .document(getAndroid_id())
     * .get()
     * .addOnCompleteListener(task -> {
     * if (task.isSuccessful()) {
     * Log.d("FireStore", "Success");
     * DocumentSnapshot document = task.getResult();
     * if (document.exists()) {
     * String userName = document.getString("userName");
     * String email = document.getString("email");
     * String phoneNumber = document.getString("phoneNumber");
     * setUser(new User(userName, email, phoneNumber));
     * }
     * } else {
     * Log.d("FireStore", "Failed with: ", task.getException());
     * }
     * });
     * }
     */
    public User getUser() {
        return this.user;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public DocumentSnapshot getDocument() {
        return document;
    }

    public void checkDocument(String collection, String document, documentCallBack documentCallBack) {
        db.collection(collection)
                .document(document)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            Log.d("FireStore", "Document exists!");
                            this.document = documentSnapshot;
                            documentCallBack.isDocumentExist(true);
                        } else {
                            Log.d("FireStore", "Document does not exists!");
                            documentCallBack.isDocumentExist(false);
                        }
                    } else {
                        documentCallBack.isDocumentExist(false);
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public void setDocument(String collection, String document, Experiment exp, documentSuccessCallBack documentSuccessCallBack) {
        db.collection(collection)
                .document(document)
                .set(exp)
                .addOnCompleteListener(task -> {
                    documentSuccessCallBack.isSuccessful(task.isSuccessful());
                });
    }

    public void setDocument(String collection, String document, User user, documentSuccessCallBack documentSuccessCallBack) {
        db.collection(collection)
                .document(document)
                .set(user)
                .addOnCompleteListener(task -> {
                    documentSuccessCallBack.isSuccessful(task.isSuccessful());
                });
    }

    public ArrayList<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(ArrayList<Experiment> experiments) {
        this.experiments = experiments;
    }

    public ArrayList<Experiment> getCreatedExps() {
        return createdExps;
    }

    public void setCreatedExps(ArrayList<Experiment> createdExps) {
        this.createdExps = createdExps;
    }

    public ArrayList<Experiment> getTargetExps() {
        return targetExps;
    }

    public void setTargetExps(ArrayList<Experiment> targetExps) {
        this.targetExps = targetExps;
    }

    public ArrayList<Question> getTargetQuestions() {
        return targetQuestions;
    }

    public void setTargetQuestions(ArrayList<Question> targetQuestions) {
        this.targetQuestions = targetQuestions;
    }

    public void addCreatedExps(Experiment experiment) {
        this.createdExps.add(experiment);
    }
}
