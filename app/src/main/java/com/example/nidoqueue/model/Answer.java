package com.example.nidoqueue.model;

/**
 * Classname:   Answer.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Handles User Answers in the Forum.
 */
public class Answer {
    private String answer;

    public  Answer() {

    }

    public Answer(String answer) {
        this.answer = answer;
    }

    /**
     * This returns a answer
     *
     * @return Return the answer
     */
    public String getAnswer() {
        return answer;
    }
}
