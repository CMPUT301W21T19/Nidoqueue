package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.controller.UserControl;
import com.example.nidoqueue.model.Answer;
import com.example.nidoqueue.model.AnswerAdapter;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Question;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Classname:   QuestionActivity.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Activity displays the questions to the user.
 */
public class QuestionActivity extends AbstractActivity {
    //region class variables
    private Question question;
    private ArrayList<String> answers;
    //region UI tools
    //region Buttons
    private ImageButton btn_back, btn_home;
    private Button btn_add;
    //endregion
    private TextView text_question;
    //region ListView tools
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    //endregion
    //endregion
    //region RequestManager and ContextManager
    //these were copied from WelcomeActivity.java
    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();
    static DatabaseManager databaseManager = DatabaseManager.getInstance();
    static UserControl userControl = UserControl.getInstance();
    //endregion
    //endregion

    //region class functions
    int listPosition;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        Intent mIntent = getIntent();
        listPosition = mIntent.getIntExtra("ListPosition", 0);
        name = mIntent.getStringExtra("Experiment Name");

        question = databaseManager.getTargetQuestions().get(listPosition);
        answers = question.getAnswers();

        text_question = findViewById(R.id.display_question);
        text_question.setText(question.getQuestion());

        //region UI setup
        listView = findViewById(R.id.reply_list);
        arrayAdapter = new AnswerAdapter(this, answers);
        listView.setAdapter(arrayAdapter);

        btn_add = findViewById(R.id.add_button3);
        btn_back = findViewById(R.id.back_button9);
        //btn_home = findViewById(R.id.home_button6);

        btn_add.setOnClickListener(addReply);
        btn_back.setOnClickListener(goBack);
        //btn_home.setOnClickListener(goHome);
        //endregion
    }


    //region Database Functions
    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
    //endregion

    //region OnClickListeners

    private View.OnClickListener addReply = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(AddReplyActivity.class, listPosition, name);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ForumActivity.class, listPosition, name);
        }
    };

    @Override
    public void onBackPressed() {
        requestManager.transition(ForumActivity.class, listPosition, name);
    }

    private View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    //endregion

    //endregion
}
