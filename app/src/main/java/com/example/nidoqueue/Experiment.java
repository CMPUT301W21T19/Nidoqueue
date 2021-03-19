package com.example.nidoqueue;


import java.util.ArrayList;

public class Experiment {
    // class attributes

    private User owner;
    private String description;
    private String region;
    private int num_of_trials;
    private boolean published;
    private ArrayList<Question> questions;
    private ArrayList<User> experimenters;

    public Experiment(User owner, String description) {
        this.owner = owner;
        this.description = description;
        questions = new ArrayList<>();
        experimenters = new ArrayList<>();
    }

    public void publish() {
        this.published = true;
    }

    public void unpublish() {
        this.published = false;
    }

    public void subscribe(User user){
        experimenters.add(user);
    }

    // getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
}