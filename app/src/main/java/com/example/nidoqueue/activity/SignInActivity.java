package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.ExpListAdapter;
import com.example.nidoqueue.ExperimentCreateFragment;
import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.DatabaseManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AbstractActivity implements ExperimentCreateFragment.OnFragmentInteractionListener {

    ImageButton add, options, search;
    RecyclerView created, subscribed;

    User user;

    ArrayList<Experiment> createdExps;
    ArrayList<Experiment> subscribedExps;
    ArrayList<String> createdExpsName;
    ArrayList<String> subscribedExpsName;

    ExpListAdapter createdAdapter;
    ExpListAdapter subscribedAdapter;

    boolean doubleBackToExitPressedOnce = false;

    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = requestManager.getContextManager();
    DatabaseManager databaseManager = requestManager.getDatabaseManager();

    private View.OnClickListener optionsOnClickListener = v -> requestManager.signInOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);

        createdExps = new ArrayList<>();
        createdExpsName = new ArrayList<>();
        subscribedExps = new ArrayList<>();
        subscribedExpsName = new ArrayList<>();

        add = findViewById(R.id.create_exp_button);
        options = findViewById(R.id.options_button);
        search = findViewById(R.id.search_button1);
        add.setOnClickListener(v -> {
            new ExperimentCreateFragment(null, null, null, null, null, null, databaseManager).show(getSupportFragmentManager(), "Create Exp");
        });
        options.setOnClickListener(v -> options());
        search.setOnClickListener(v -> search());

        databaseManager.checkDocument("users", databaseManager.getAndroid_id(), exist -> {
            if (exist) {
                Object object = databaseManager.getDocument().getData();
                String json = new Gson().toJson(object);
                Log.v("json", json);
                JSONArray expArray;
                try {
                    expArray = new JSONObject(json).getJSONArray("createdExp");
                    Log.v("json", expArray.toString());
                    Log.d("Length", String.valueOf(expArray.length()));
                    int i = 0;
                    while (i < expArray.length()) {
                        Log.d("Update", "Progress");

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

                    expArray = new JSONObject(json).getJSONArray("subscribedExp");
                    Log.v("json", expArray.toString());
                    Log.d("Length", String.valueOf(expArray.length()));
                    i = 0;
                    while (i < expArray.length()) {
                        Log.d("Update", "Progress");

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
                    createdExpsName = new ArrayList<>();
                    for (Experiment exps : createdExps) {
                        createdExpsName.add(exps.getName());
                        Log.d("Name", exps.getName());
                    }

                    createdAdapter = new ExpListAdapter(createdExpsName);

                    created = findViewById(R.id.created_exps_list);
                    created.setLayoutManager(new LinearLayoutManager(this));
                    created.setAdapter(createdAdapter);

                    subscribedExpsName = new ArrayList<>();
                    for (Experiment exps : subscribedExps) {
                        subscribedExpsName.add(exps.getName());
                        Log.d("Name", exps.getName());
                    }

                    subscribedAdapter = new ExpListAdapter(subscribedExpsName);

                    subscribed = findViewById(R.id.sub_exps_list);
                    subscribed.setLayoutManager(new LinearLayoutManager(this));
                    subscribed.setAdapter(subscribedAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            finishAffinity();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
//    }

    public void options() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void search() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onOkPressed(Experiment exp, String type) {
        databaseManager.checkDocument("experiments", exp.getName().toLowerCase(), exist -> {
            if (exist) {
                Toast.makeText(getApplicationContext(), "Name already exists!\nTry other experiment name", Toast.LENGTH_LONG).show();
            } else {
                user.createExp(exp);
//                dbManager.setDocument("experiments", exp.getName().toLowerCase(), exp, success -> {
//                    Map<String, String> ownerId = new HashMap<>();
//                    ownerId.put("owner", android_id);
//                    db.collection("experiments")
//                            .document(exp.getName().toLowerCase())
//                            .set(ownerId, SetOptions.merge());
//
//                    db.collection("users")
//                            .document(android_id)
//                            .update("createdExp", FieldValue.arrayUnion(exp));
//
//                    createdExpsName.add(exp.getName());
//                    createdAdapter.notifyDataSetChanged();
//                });
            }
        });
    }
}

