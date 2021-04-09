package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.ForumAdapter;
import com.example.nidoqueue.model.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname:   ForumActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity displays the questions & answers to the user.
 */
public class ForumActivity extends AbstractActivity {
    //region Class variables
    private ArrayList<Question> questions;
    //region UI tools
    //region Buttons
    private Button btn_add;
    private ImageButton btn_home;
    private ImageButton btn_back;
    //endregion
    //region ListView tools
    private ListView listView;
    private ArrayAdapter<Question> arrayAdapter;
    //endregion
    //endregion
    //region RequestManager and ContextManager
    //these were copied from WelcomeActivity.java
    String expName;
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final UserControl userControl = UserControl.getInstance();
    //endregion
    //endregion

    //region class functions
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);
        Intent mIntent = getIntent();
        expName = mIntent.getStringExtra("Experiment Name");

        questions = new ArrayList<>(); //REPLACE ME!

        //region UI setup
        listView = findViewById(R.id.forum_info);

        databaseManager.getDb().collection("experiments")
                .document(this.expName.toLowerCase())
                .get()
                .addOnCompleteListener(task -> {
                    List<Question> questions = task.getResult().toObject(QuestionDocument.class).questions;
                    this.questions.addAll(questions);

                    arrayAdapter = new ForumAdapter(this, this.questions);
                    listView.setAdapter(arrayAdapter);
                });

        btn_add = findViewById(R.id.add_button2);
        btn_back = findViewById(R.id.back_button7);
        btn_home = findViewById(R.id.home_button4);

        btn_add.setOnClickListener(addQuestion);
        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        listView.setOnItemClickListener(selectQuestion);
        //endregion
    }

    //region Database Functions
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion

    //region OnClickListeners - some functionality required
    private AdapterView.OnItemClickListener selectQuestion = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            database.setTargetQuestions(questions);
            requestManager.transition(QuestionActivity.class, position, expName);
            //will need way to specify question
        }
    };
    private View.OnClickListener addQuestion = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            requestManager.transition(AddQuestionActivity.class, expName);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ExperimentActivity.class);
        }
    };

    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    //endregion

    //endregion
}

class QuestionDocument {
    public List<Question> questions;
}
