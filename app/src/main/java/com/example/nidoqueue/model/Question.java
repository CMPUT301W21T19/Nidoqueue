package com.example.nidoqueue.model;

import java.util.ArrayList;

public class Question {

    private String question;
    private ArrayList<Answer> answers;

    public Question(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    public void reply(Answer ans) {
        answers.add(ans);
    }
}
