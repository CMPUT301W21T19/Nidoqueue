package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname:   SignInActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity displays created and subscribed experiments to the user.
 * Functions as a main menu for the signed in user.
 */
public class SignInActivity extends AbstractActivity implements ExperimentCreateFragment.OnFragmentInteractionListener, RecyclerViewClickListener {
    ImageButton createExp, profile, search;
    TextView title;
    RecyclerView createdExpRecyclerView, subscribedExpRecyclerView;
    User user;

    ArrayList<Experiment> createdExps, subscribedExps;
    ExpListAdapter createdExpListAdapter, subscribedExpListAdapter;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void recyclerViewListClicked(View v, int position) {
        if (((RecyclerView) v.getParent()).getId() == R.id.created_exps_list) {
            databaseManager.setTargetExps(createdExpListAdapter.list);
        } else {
            databaseManager.setTargetExps(subscribedExpListAdapter.list);
        }
        requestManager.transition(ExperimentActivity.class, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);
        requestManager.setPreviousActivity(SignInActivity.class);
        user = userControl.getUser();

        createExp = findViewById(R.id.create_exp_button);
        profile = findViewById(R.id.profile_button);
        search = findViewById(R.id.search_button1);

        profile.setOnClickListener(Profile);
        search.setOnClickListener(Search);
        createExp.setOnClickListener(CreateExp);

        String titleText = "Welcome " + databaseManager.getUser().getUsername();
        title = findViewById(R.id.welcome_user_title);
        title.setSelected(true);
        title.setText(titleText);

        createdExps = new ArrayList<>();
        subscribedExps = new ArrayList<>();

        createdExpRecyclerView = findViewById(R.id.created_exps_list);
        subscribedExpRecyclerView = findViewById(R.id.sub_exps_list);

        createdExpRecyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), R.drawable.divider));
        subscribedExpRecyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), R.drawable.divider));

        //Get created and subscribed experiments from databse for the user.
        databaseManager.getDb().collection("experiments")
                .get()
                .addOnCompleteListener(task -> {
                    Experiment experiment = null;
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        if (documentSnapshot.getString("owner").equals(databaseManager.getAndroid_id())) {
                            String expType = documentSnapshot.getString("type");
                            switch (expType) {
                                case "count":
                                    experiment = documentSnapshot.toObject(ExpCount.class);
                                    break;
                                case "binomial":
                                    experiment = documentSnapshot.toObject(ExpBinomial.class);
                                    break;
                                case "nonNegative":
                                    experiment = documentSnapshot.toObject(ExpNonNegative.class);
                                    break;
                                case "measurement":
                                    experiment = documentSnapshot.toObject(ExpMeasurement.class);
                                    break;
                            }
                            createdExps.add(experiment);
                        }
                    }
                    databaseManager.setCreatedExps(createdExps);
                    createdExpListAdapter = new ExpListAdapter(createdExps, SignInActivity.this);

                    createdExpRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    createdExpRecyclerView.setAdapter(createdExpListAdapter);
                });

        databaseManager.getDb().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        List<String> subExps = (List<String>) documentSnapshot.get("subscribedExp");
                        if (subExps != null) {
                            for (String name : subExps) {
                                for (Experiment exp : databaseManager.getExperiments()) {
                                    if (exp.getName().toLowerCase().equals(name)) {
                                        subscribedExps.add(exp);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    subscribedExpListAdapter = new ExpListAdapter(subscribedExps, SignInActivity.this);

                    subscribedExpRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    subscribedExpRecyclerView.setAdapter(subscribedExpListAdapter);
                });
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
        requestManager.addExperiment(exp, type, createdExpListAdapter);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            ActivityCompat.finishAffinity(this);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return databaseManager.getDb();
    }
}
