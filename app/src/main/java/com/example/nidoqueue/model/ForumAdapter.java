package com.example.nidoqueue.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nidoqueue.R;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Classname:   ForumAdapter.java
 * Version:     Final
 * Date:        April 9, 2021
 * Purpose:     ArrayAdapter for the question and answer forum.
 */
public class ForumAdapter extends ArrayAdapter<Question> {
    private ArrayList<Question> questions;
    private Context context;

    public ForumAdapter(@NonNull Context context, @NonNull ArrayList<Question> questions){
        super(context, 0, questions);
        this.questions = questions;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.forum_content, parent, false);
        }

        Question question = questions.get(position);

        TextView questionPreview = view.findViewById(R.id.question_preview);
        TextView totalAnswer = view.findViewById(R.id.answer_total);

        questionPreview.setText(question.getQuestionPreview(80));
        totalAnswer.setText(String.valueOf(question.getTotalAnswers()));

        return view;
    }
}
