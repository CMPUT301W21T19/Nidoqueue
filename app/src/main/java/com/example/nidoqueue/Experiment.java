package com.example.nidoqueue;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Experiment {
    // class attributes

    //private User owner;
    private String description;
    private String Region;
    private int num_of_trials;
    private boolean published;
    //private QuestionList questons;
    //private ArrayList<Trials> trials;
    //private ArrayList<User> experimenterList;


    public void publish(){
        this.published = true;
    }

    public void unpublish(){
        this.published = false;
    }

//    public void subscribe(User user){
//
//    }
    // getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public int getNum_of_trials() {
        return num_of_trials;
    }

    public void setNum_of_trials(int num_of_trials) {
        this.num_of_trials = num_of_trials;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }