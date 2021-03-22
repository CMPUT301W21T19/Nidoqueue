package com.example.nidoqueue;

import java.util.ArrayList;

/**
 * Classname:   Question.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles User Questions in the Forum.
 * Issues:      Incomplete. This will be worked on in the future.
 */
public class Question {

    private String question;
    private ArrayList<Answer> answers;

    public Question(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    /**
     * This adds a reply(answer) to the question
     *
     * @param ans This is a candidate answer to the question.
     */
    public void reply(Answer ans) {
        answers.add(ans);
    }
}
