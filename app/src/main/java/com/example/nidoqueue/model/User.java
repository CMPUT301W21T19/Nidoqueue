package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname: 	User.java
 * Version:		Prototype
 * Date:		March 19th, 2021
 * Purpose:		Class for user class that holds user information
 * Issues: 		None
 */
public class User {

    private String userName;
    private String email;
    private String phoneNumber;
    private ArrayList<Experiment> createdExperiments;
    private ArrayList<Experiment> subscribedExperiments;

    public User() {
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
        createdExperiments = new ArrayList<>();
        subscribedExperiments = new ArrayList<>();
    }

    public User(String userName, String email, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        createdExperiments = new ArrayList<>();
        subscribedExperiments = new ArrayList<>();
    }

    /**
     * This adds an experiment to the list that the user created and being owner
     *
     * @param experiment This is a candidate experiment to add
     */
    public void createExp(Experiment experiment) {
        createdExperiments.add(experiment);
    }

    /**
     * This adds an experiment to the list that the user subscribed
     *
     * @param experiment This is a candidate experiment to add
     */
    public void subscribeExp(Experiment experiment) {
        subscribedExperiments.add(experiment);
    }

    /**
     * This returns a username of the user
     *
     * @return Return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This returns a email of the user
     *
     * @return Return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This sets an email of the user
     *
     * @param email This is a email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This returns a phone number of the user
     *
     * @return Return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This sets a phone number of the user
     *
     * @param phoneNumber This is a phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This returns the list of experiment that user created
     *
     * @return Return the array with created experiment
     */
    public ArrayList<Experiment> getCreatedExp() {
        return createdExperiments;
    }

    /**
     * This returns the list of experiment that user subscribed
     *
     * @return Return the array with subcribed experiment
     */
    public ArrayList<Experiment> getSubscribedExp() {
        return subscribedExperiments;
    }

    /**
     * This sets a array of experiments that user created
     *
     * @param createdExperiments This is a candidate list to set.
     */
    public void setCreatedExp(ArrayList<Experiment> createdExperiments) {
        this.createdExperiments = createdExperiments;
    }

    /**
     * This sets a array of experiments that user subscribed
     *
     * @param subscribedExperiments This is a candidate list to set
     */
    public void setSubscribedExp(ArrayList<Experiment> subscribedExperiments) {
        this.subscribedExperiments = subscribedExperiments;
    }
}
