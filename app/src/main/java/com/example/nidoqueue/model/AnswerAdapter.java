package com.example.nidoqueue.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.nidoqueue.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class AnswerAdapter extends ArrayAdapter<Answer> {

    private ArrayList<Answer> answers;
    private Context context;

    public AnswerAdapter(@NonNull Context context, @NonNull ArrayList<Answer> answers){
        super(context, 0, answers);
        this.answers = answers;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.forum_content, parent, false);
        }

       Answer answer = answers.get(position);

        TextView dispAnswer = view.findViewById(R.id.answer_display);

        dispAnswer.setText(answer.getAnswer());

        return view;
    }


}
