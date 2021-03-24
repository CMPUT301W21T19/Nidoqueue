package com.example.nidoqueue.model;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Classname:   DatabaseManager.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Sets up the Firestore which handles our database and retrieving user information.
 * Issues:      No issues currently.
 */
public class Database {

    FirebaseFirestore db;
    String android_id;
    User user;

    private static final Database database = new Database();
    private static final ContextManager contextManager = ContextManager.getInstance();

    private Database() {
        db = FirebaseFirestore.getInstance();
//        android_id = Settings.Secure.getString(contextManager.getActivity().getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);

//        updateUser();
    }
    public static Database getInstance() {
        return database;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void updateUser() {
        getDb().collection("users")
                .document(getAndroid_id())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("FireStore", "Success");
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String userName = document.getString("userName");
                            String email = document.getString("email");
                            String phoneNumber = document.getString("phoneNumber");
                            setUser(new User(userName, email, phoneNumber));
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }

    public User getUser() {
        updateUser();
        return user;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public void readDocument(DocumentSnapshot document) {
        Object object = document.getData();
        String json = new Gson().toJson(object);
        Log.v("json", json);

        ArrayList<Experiment> createdExps;
        ArrayList<String> createdExpsName;
        createdExps = new ArrayList<>();
        createdExpsName = new ArrayList<>();
        RecyclerView created, subscribed;

        try {
            JSONArray expArray = new JSONObject(json).getJSONArray("createdExp");
            Log.v("json", expArray.toString());
            Log.d("Length", String.valueOf(expArray.length()));
            int i = 0;
            while (i < expArray.length()) {
                Log.d("Update", "Progress");
                //String key = iterator.next();
                //SONObject objArray = expArray.getJSONObject(key);
                String expType = expArray.getJSONObject(i).getString("type");
                Log.d("Type", expType);
                Experiment experiment = null;
                switch (expType) {
                    case "count":
                        experiment = new ExpCount(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                        break;
                    case "binomial":
                        experiment = new ExpBinomial(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                        break;
                    case "nonNegative":
                        experiment = new ExpNonNegative(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                        break;
                    case "measurement":
                        experiment = new ExpMeasurement(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getString("unit"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                        break;
                }
                createdExps.add(experiment);
                i++;
            }

            for (Experiment exps : createdExps) {
                createdExpsName.add(exps.getName());
                Log.d("Name", exps.getName());
            }
            ExpListAdapter adapter;
            adapter = new ExpListAdapter(createdExpsName);

            created = contextManager.getActivity().findViewById(R.id.created_exps_list);
            created.setLayoutManager(new LinearLayoutManager(contextManager.getContext()));
            created.setAdapter(adapter);

            subscribed = contextManager.getActivity().findViewById(R.id.sub_exps_list);
            subscribed.setLayoutManager(new LinearLayoutManager(contextManager.getContext()));
            subscribed.setAdapter(adapter);

//                                DividerItemDecoration dividerItemDecoration =
//                                        new DividerItemDecoration(created.getContext(),new LinearLayoutManager(this).getOrientation());
//                                created.addItemDecoration(dividerItemDecoration);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
