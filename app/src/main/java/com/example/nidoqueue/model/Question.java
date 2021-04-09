package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname:   Question.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Handles User Questions in the Forum.
 */
public class Question {

    private String question;
    private ArrayList<String> answers;

    public Question() {

    }

    public Question(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    /**
     * This adds a reply(answer) to the question
     *
     * @param ans This is a candidate answer to the question.
     */
    public void reply(String ans) {
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

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //endregion
}
