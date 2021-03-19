package com.example.nidoqueue;


import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public abstract class Experiment {
    // class attributes

    private User owner;
    private String name;
    private String description;
    private String region;
    private int num_of_trials;
    private boolean geoLocation;
    private boolean published;
    private ArrayList<Question> questions;
    private ArrayList<User> experimenters;

    public Experiment() {

    }

    public Experiment(User owner, String name, String description, Boolean geoLocation) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.geoLocation = geoLocation;
        questions = new ArrayList<>();
        experimenters = new ArrayList<>();
        published = true;
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
    @Exclude
    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

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

    public boolean getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(Boolean geoLocation) {
        this.geoLocation = geoLocation;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public abstract String getType();

    public abstract ArrayList<Trial> getTrials();
}
