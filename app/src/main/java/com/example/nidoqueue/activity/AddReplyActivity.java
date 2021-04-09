package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.Answer;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Question;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Classname:   AddReplyActivity.java
 * Version:     Final
 * Date:        Apr 9th, 2021
 * Purpose:     Activity handles the reply process.
 */
public class AddReplyActivity extends AbstractActivity {

    //region class variables
    //region UI elements
    private ImageButton btn_back, btn_home;
    private Button btn_post;
    private EditText editText_reply;
    //endregion
    //region RequestManager and ContextManager
    //these were copied from WelcomeActivity.java
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    UserControl userControl = UserControl.getInstance();
    //endregion
    private Question question; //need Database functionality to get question
    //endregion

    int listPosition;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reply);

        Intent mIntent = getIntent();
        listPosition = mIntent.getIntExtra("ListPosition", 0);
        name = mIntent.getStringExtra("Experiment Name");

        //region buttons
        btn_back = findViewById(R.id.back_button10);
        btn_home = findViewById(R.id.home_button7);
        btn_post = findViewById(R.id.post_button2);
        editText_reply = findViewById(R.id.enter_reply);

        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
        btn_post.setOnClickListener(postReply);
        //endregion
    }


    //region OnClickListeners

    //functionality for postQuestion required
    private View.OnClickListener postReply = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String stringAnswer = editText_reply.getText().toString();
            Answer answer = new Answer(stringAnswer);
            // add database functionality
            databaseManager.getTargetQuestions().get(listPosition).reply(answer);
            Log.d("Question", databaseManager.getTargetQuestions().get(listPosition).getQuestion());
            Log.d("Answer", databaseManager.getTargetQuestions().get(listPosition).getAnswers().get(0).getAnswer());

            databaseManager.getDb().collection("experiments")
                    .document(name.toLowerCase())
                    .update("questions", FieldValue.arrayUnion(databaseManager.getTargetQuestions()));

            finish();
            requestManager.transition(QuestionActivity.class, listPosition);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            requestManager.transition(QuestionActivity.class, listPosition);
        }
    };

    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    //endregion

    //region Database functions
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion
}
