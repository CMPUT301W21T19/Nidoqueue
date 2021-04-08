package com.example.nidoqueue.model;

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

    //region GETTERS
    public String getQuestionPreview(int maxSize){
        if(question.length() >= maxSize){
            return question.substring(0,maxSize);
        }
        return question;
    }

    public int getTotalAnswers(){
        return answers.size();
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    //endregion
}
