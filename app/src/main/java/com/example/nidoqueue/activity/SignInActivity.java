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
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
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
    ExpListAdapter expListAdapter;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);
        user = userControl.getUser();

        createdExps = new ArrayList<>();

        addExp = findViewById(R.id.create_exp_button);
        profile = findViewById(R.id.profile_button);
        search = findViewById(R.id.search_button1);

        profile.setOnClickListener(Profile);
        search.setOnClickListener(Search);
        addExp.setOnClickListener(AddExp);
        expListAdapter = new ExpListAdapter(createdExps, ExpClickListener);
        created = findViewById(R.id.created_exps_list);
        created.setLayoutManager(new LinearLayoutManager(this));
        created.setAdapter(expListAdapter);
//        subscribed = findViewById(R.id.sub_exps_list);
//        subscribed.setLayoutManager(new LinearLayoutManager(this));
//        subscribed.setAdapter(adapter);
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
            requestManager.createExp();
        }
    };

    @Override
    public void onOkPressed(Experiment exp, String type) {

        requestManager.addExperiment(exp, type, expListAdapter);
//        requestManager.addExp();
    }


    private View.OnClickListener ExpClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            requestManager.transition(R.layout.experiment, ExperimentActivity.class);
        }
    };
//    public void populateList(){
//        //populateList_Firebase();
//
//        expListAdapter = new ExpListAdapter(createdExps, ExpClickListener);
//        created = findViewById(R.id.created_exps_list);
//        created.setLayoutManager(new LinearLayoutManager(this));
//        created.setAdapter(expListAdapter);
//        subscribed = findViewById(R.id.sub_exps_list);
//        subscribed.setLayoutManager(new LinearLayoutManager(this));
//        subscribed.setAdapter(expListAdapter);
//    }

    public FirebaseFirestore getDB() {
        return databaseManager.getDb();
    }
}
