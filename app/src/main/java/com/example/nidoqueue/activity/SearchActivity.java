package com.example.nidoqueue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   SearchActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that handles the OnClickListeners for the search function.
 * Issues:      Incomplete
 */
import com.example.nidoqueue.R;

public class SearchActivity extends AbstractActivity implements RecyclerViewClickListener  {
    ImageButton searchBar, backButton;
    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();

    ArrayList<Experiment> exps;

    @Override
    public void recyclerViewListClicked(View v, int position) {
        requestManager.transition(SearchActivity.class, position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_trials);
        contextManager.setContext(this);

        searchBar = findViewById(R.id.search_button2);
        searchBar.setOnClickListener(SearchBar);

        backButton = findViewById(R.id.back_button3);
        backButton.setOnClickListener(Back);

        exps = new ArrayList<>();
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
            requestManager.back();
        }
    };
    /******************************************************************************
     * Firebase Database Code
     ******************************************************************************/
    public FirebaseFirestore getDB() {
        return null;
    }
}
