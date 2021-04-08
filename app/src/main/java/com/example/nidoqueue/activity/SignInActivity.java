package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

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

/**
 * Classname:   SignInActivity.java
 * Version:     Prototype
 * Date:        Apr 9th, 2021
 * Purpose:     Activity to show the list of created and subscribed experiments of the user.
 * Issues:      Non-functional, planning stages.
 */

public class SignInActivity extends AbstractActivity implements ExperimentCreateFragment.OnFragmentInteractionListener, RecyclerViewClickListener {
    ImageButton createExp, profile, search;
    RecyclerView created, subscribed;
    User user;

    static ArrayList<Experiment> createdExps;
    ExpListAdapter expListAdapter;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    @Override
    public void recyclerViewListClicked(View v, int position) {
        requestManager.transition(ExperimentActivity.class, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);
        user = userControl.getUser();

        createdExps = new ArrayList<>();

        createExp = findViewById(R.id.create_exp_button);
        profile = findViewById(R.id.profile_button);
        search = findViewById(R.id.search_button1);

        profile.setOnClickListener(Profile);
        search.setOnClickListener(Search);
        createExp.setOnClickListener(CreateExp);

        expListAdapter = new ExpListAdapter(createdExps, this);
        created = findViewById(R.id.created_exps_list);
        created.setLayoutManager(new LinearLayoutManager(this));
        created.setAdapter(expListAdapter);

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
    private View.OnClickListener CreateExp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.createExp();
        }
    };

    @Override
    public void onOkPressed(Experiment exp, String type) {
        requestManager.addExperiment(exp, type, expListAdapter);
    }


    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return databaseManager.getDb();
    }
}
