package com.example.nidoqueue.activity;

import android.os.Bundle;
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
import com.example.nidoqueue.model.DatabaseAlt;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.Question;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname:   QuestionActivity.java
 * Version:     Prototype
 * Date:        April 8th, 2021
 * Purpose:     Activity that displays the question segment of Q&A Forum
 */
public class QuestionActivity extends AbstractActivity{
    //region class variables
    private Question question;
    private ArrayList<Answer> answers;
    //region UI tools
    //region Buttons
    private ImageButton btn_back, btn_home;
    private Button btn_add;
    //endregion
    private TextView text_question;
    //region ListView tools
    private ListView listView;
    private ArrayAdapter<Answer> arrayAdapter;
    //endregion
    //endregion
    //region RequestManager and ContextManager
    //these were copied from WelcomeActivity.java
    private static final RequestManager requestManager = RequestManager.getInstance();
    private static final ContextManager contextManager = ContextManager.getInstance();
    private static final DatabaseManager database = DatabaseManager.getInstance();
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final DatabaseAlt databaseAlt = DatabaseAlt.getInstance();
    private static final UserControl userControl = UserControl.getInstance();
    //endregion
    //endregion

    //region class functions

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        question = new Question("REPLACE ME"); //REPLACE ME!
        answers = question.getAnswers();

        //region UI setup
        listView = findViewById(R.id.reply_list);
        arrayAdapter = new AnswerAdapter(this, answers);
        listView.setAdapter(arrayAdapter);

        btn_add = findViewById(R.id.add_button3);
        btn_back = findViewById(R.id.back_button9);
        btn_home = findViewById(R.id.home_button6);

        btn_add.setOnClickListener(addReply);
        btn_back.setOnClickListener(goBack);
        btn_home.setOnClickListener(goHome);
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
            requestManager.transition(AddReplyActivity.class);
        }
    };

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class); //CHANGE ME
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
