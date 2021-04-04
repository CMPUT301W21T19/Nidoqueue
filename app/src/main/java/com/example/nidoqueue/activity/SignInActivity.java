package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.Database;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignInActivity extends AbstractActivity implements ExperimentCreateFragment.OnFragmentInteractionListener {
    ImageButton addExp, profile, search;
    RecyclerView created, subscribed;
    User user;

    ArrayList<Experiment> createdExps;
    ArrayList<String> createdExpsName;
    ExpListAdapter adapter;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    static Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);
        user = userControl.getUser();

        createdExps = new ArrayList<>();
        createdExpsName = new ArrayList<>();

        addExp = findViewById(R.id.create_exp_button);
        profile = findViewById(R.id.profile_button);
        search = findViewById(R.id.search_button1);

        profile.setOnClickListener(Profile);
        search.setOnClickListener(Search);
        addExp.setOnClickListener(AddExp);

        //requestManager.populateList();
        //populateList();
    }

    private View.OnClickListener Profile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.profile();
        }
    };

    private View.OnClickListener Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.search();
        }
    };
    private View.OnClickListener AddExp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.addExp();
        }
    };

    @Override
    public void onOkPressed(Experiment exp, String type) {
        //Adapter.add(newUser);
        //requestManager.addExperiment(exp, type);
        requestManager.addExp();
    }

    public void populateList(){
        //populateList_Firebase();
        createdExpsName = new ArrayList<>();
        for (Experiment exps : createdExps) {
            createdExpsName.add(exps.getName());
            Log.d("Name", exps.getName());
        }
        adapter = new ExpListAdapter(createdExpsName);
        created = findViewById(R.id.created_exps_list);
        created.setLayoutManager(new LinearLayoutManager(this));
        created.setAdapter(adapter);
        subscribed = findViewById(R.id.sub_exps_list);
        subscribed.setLayoutManager(new LinearLayoutManager(this));
        subscribed.setAdapter(adapter);
    }

    public FirebaseFirestore getDB() {
        return database.getDb();
    }


    /**
    public void populateList_Firebase(){
        database.getDb().collection("users")
                .document(android_id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Object object = document.getData();
                            String json = new Gson().toJson(object);
                            Log.v("json", json);
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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
     */
}
