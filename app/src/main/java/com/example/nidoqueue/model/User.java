package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname: 	User.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Class for user class that holds user information.
 */
public class User {
    private String username, email, password;
    private ArrayList<Experiment> createdExp, subscribedExp;
    /******************************************************************************
     * Setter methods. Contains "Username, Email, and Phone Number"
     ******************************************************************************/
    public User(String username, String email, String password, ArrayList<Experiment> createdExp, ArrayList<Experiment> subscribedExp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdExp = createdExp;
        this.subscribedExp = subscribedExp;
        if(createdExp == null){
            this.createdExp = new ArrayList<>();
        }
        if(subscribedExp == null){
            this.subscribedExp = new ArrayList<>();
        }

    }
    /******************************************************************************
     * Getter methods. Contains "Username, Email, and Phone Number"
     ******************************************************************************/
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    } // I (Spencer) strongly believe that if we aren't hashing passwords, that we shouldn't have passwords at all
    public ArrayList<Experiment> getCreatedExp() {
        return createdExp;
    }
    public ArrayList<Experiment> getSubscribedExp() {
        return subscribedExp;
    }
    /******************************************************************************
     * Extra Methods
     ******************************************************************************/
    public void createExp(Experiment experiment) {
        createdExp.add(experiment);
    }
    public void subscribeExp(Experiment experiment) {
        subscribedExp.add(experiment);
    }

    public void setCreatedExp(ArrayList<Experiment> createdExperiments) {
        this.createdExp = createdExperiments;
    }
    public void setSubscribedExp(ArrayList<Experiment> subscribedExperiments) {
        this.subscribedExp = subscribedExperiments;
    }
    public void addSubscribedExp(Experiment experiment) {
        subscribedExp.add(experiment);

    }
    public void remSubscribedExp(Experiment experiment) {
        subscribedExp.remove(experiment);
    }

}
