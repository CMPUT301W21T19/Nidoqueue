package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.Question;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   AddReplyActivity.java
 * Version:     Final
 * Date:        Apr 9th, 2021
 * Purpose:     Activity handles the question process.
 */
public class AddQuestionActivity extends AbstractActivity {

    //region class variables
    //region UI elements
    private ImageButton btn_back, btn_home;
    private Button btn_post;
    private EditText editText_question;
    //endregion
    //region RequestManager and ContextManager
    //these were copied from WelcomeActivity.java
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final UserControl userControl = UserControl.getInstance();
    //endregion
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_question);

        //region UI setup
        btn_back = findViewById(R.id.back_button8);
        btn_home = findViewById(R.id.home_button5);
        btn_post = findViewById(R.id.post_button);
        editText_question = findViewById(R.id.enter_question);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_post.setOnClickListener(postQuestion);
        //endregion
    }

    //region Database functions
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion



    private View.OnClickListener postQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Posting Question to the Database
            Question question = new Question(editText_question.getText().toString());
            Intent mIntent = getIntent();
            String name = mIntent.getStringExtra("Experiment Name");
            for (Experiment experiment : databaseManager.getExperiments()) {
                if (name.equals(experiment.getName())) {
                    experiment.addQuestion(question);
                    databaseManager.getDb().collection("experiments")
                            .document(experiment.getName().toLowerCase())
                            .update("questions", FieldValue.arrayUnion(question));
                }
            }
            requestManager.transition(ForumActivity.class, name);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ForumActivity.class);
        }
    };

    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    //endregion
}
