package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classname:   SearchActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the OnClickListeners for the search function.
 * Issues:      Incomplete
 */

public class SearchActivity extends AbstractActivity implements SearchFragment.OnFragmentInteractionListener, RecyclerViewClickListener {
    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    ImageButton searchBar, backButton;
    Button clear;

    ArrayList<Experiment> exps, search_result;
    RecyclerView result;
    ExpSearchAdapter expAdapter;

    @Override
    public void recyclerViewListClicked(View v, int position) {
        requestManager.transition(SearchActivity.class, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_experiments);
        contextManager.setContext(this);

        searchBar = findViewById(R.id.search_button2);
        searchBar.setOnClickListener(SearchBar);
        backButton = findViewById(R.id.back_button3);
        backButton.setOnClickListener(Back);

        clear = findViewById(R.id.clear);
        clear.setOnClickListener(Clear);

        result = findViewById(R.id.search_result);

        exps = new ArrayList<>();
        search_result = new ArrayList<>();

        databaseManager.getDb().collection("experiments")
                .get()
                .addOnCompleteListener(task -> {
                    Experiment experiment = null;
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
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
                        exps.add(experiment);
                        Log.d("Test", experiment.getName());
                    }
//                    Collections.sort(exps, new NameAscending());
                    expAdapter = new ExpSearchAdapter(exps, this);

                    result.setLayoutManager(new LinearLayoutManager(this));
                    result.setAdapter(expAdapter);
                });

        result.addItemDecoration(
                new RecyclerViewDivider(getContext(), R.drawable.divider));
    }

    private View.OnClickListener SearchBar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.searchBar();
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            requestManager.back();
        }
    };
    private View.OnClickListener Clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            expAdapter = new ExpSearchAdapter(exps, SearchActivity.this);
            result.setAdapter(expAdapter);
        }
    };


    @Override
    public void onBackPressed() {
        finish();
        requestManager.back();
    }

    @Override
    public void onOkPressed(String keyword) {
        search_result.clear();
        for (Experiment exp : exps) {
            if (exp.getName().toLowerCase().contains(keyword.toLowerCase()) || exp.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                search_result.add(exp);
            }
            expAdapter = new ExpSearchAdapter(search_result, this);
            result.setAdapter(expAdapter);
        }
    }

    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
}

class NameAscending implements Comparator<Experiment> {
    @Override
    public int compare(Experiment e1, Experiment e2) {
        return e1.getName().toLowerCase().compareTo(e2.getName().toLowerCase());
    }
}

class NameDescending implements Comparator<Experiment> {
    @Override
    public int compare(Experiment e1, Experiment e2) {
        return e2.getName().toLowerCase().compareTo(e1.getName().toLowerCase());
    }
}

class TypeAscending implements Comparator<Experiment> {
    @Override
    public int compare(Experiment e1, Experiment e2) {
        return e1.getType().compareTo(e2.getType());
    }
}

class TypeDescending implements Comparator<Experiment> {
    @Override
    public int compare(Experiment e1, Experiment e2) {
        return e2.getType().compareTo(e1.getType());
    }
}
