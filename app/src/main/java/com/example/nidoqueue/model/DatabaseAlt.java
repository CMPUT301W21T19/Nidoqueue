package com.example.nidoqueue.model;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DatabaseAlt {
    private static final DatabaseAlt database = new DatabaseAlt();
    public static DatabaseAlt getInstance() {
        return database;
    }
    ArrayList<Experiment> expList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();

    /******************************************************************************
     * User Arrays
     ******************************************************************************/
    public void add_UserDB(User newUser){
        userList.add(newUser);
    }
    /******************************************************************************
     * Experiment Arrays
     ******************************************************************************/
    public void add_experimentDB(Experiment newExp){
        expList.add(newExp);
    }
    /******************************************************************************
     * Forum Arrays
     ******************************************************************************/

}
