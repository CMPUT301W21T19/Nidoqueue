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
    private String username, email, password;
    private ArrayList<Experiment> createdExp, subscribedExp;
    /******************************************************************************
     * Setter methods. Contains "Username, Email, and Password"
     ******************************************************************************/
    public User(String username, String email, String password, ArrayList<Experiment> createdExp, ArrayList<Experiment> subscribedExp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdExp = createdExp;
        this.subscribedExp = subscribedExp;
        //createdExp = new ArrayList<>();
       // subscribedExp = new ArrayList<>();
    }
    /******************************************************************************
     * Getter methods. Contains "Username, Email, and Password"
     ******************************************************************************/
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Experiment> getCreatedExp() {
        return createdExp;
    }
    public ArrayList<Experiment> getSubscribedExp() {
        return subscribedExp;
    }
    /******************************************************************************
     * Dead Code --- Dead Code --- Dead Code
     ******************************************************************************/
    public void createExp(Experiment experiment) {
        createdExp.add(experiment);
    }
    public void subscribeExp(Experiment experiment) {
        subscribedExp.add(experiment);
    }



}
