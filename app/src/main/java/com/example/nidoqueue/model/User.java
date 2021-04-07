package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname: 	User.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Class for user class that holds user information
 * Issues: 		None
 */
public class User {
    private String userName, email, phoneNumber;
    private ArrayList<Experiment> createdExperiments, subscribedExperiments;
    /******************************************************************************
     * Setter methods. Contains "Username, Email, and Phone Number"
     ******************************************************************************/
    public User(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        createdExperiments = new ArrayList<>();
        subscribedExperiments = new ArrayList<>();
    }
    /******************************************************************************
     * Getter methods. Contains "Username, Email, and Phone Number"
     ******************************************************************************/
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /******************************************************************************
     * Dead Code --- Dead Code --- Dead Code
     ******************************************************************************/
    public void createExp(Experiment experiment) {
        createdExperiments.add(experiment);
    }
    public void subscribeExp(Experiment experiment) {
        subscribedExperiments.add(experiment);
    }

    public ArrayList<Experiment> getCreatedExp() {
        return createdExperiments;
    }
    public ArrayList<Experiment> getSubscribedExp() {
        return subscribedExperiments;
    }

    public void setCreatedExp(ArrayList<Experiment> createdExperiments) {
        this.createdExperiments = createdExperiments;
    }
    public void setSubscribedExp(ArrayList<Experiment> subscribedExperiments) {
        this.subscribedExperiments = subscribedExperiments;
    }
    public void addSubscribedExp(Experiment experiment) {
        subscribedExperiments.add(experiment);
    }
}
