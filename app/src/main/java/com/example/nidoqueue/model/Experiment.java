package com.example.nidoqueue.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Classname:   Experiment.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     This is the main experiment class. Backbone of the experiments for this project.
 */
@IgnoreExtraProperties
public abstract class Experiment implements Comparable<Experiment> {
    // class attributes

    private User owner;
    private String name;
    private String description;
    private String region;
    private int num_of_trials;
    private boolean geoLocation;
    private boolean published;
    private boolean ended;
    private ArrayList<Question> questions;
    private ArrayList<User> experimenters;

    public Experiment() {

    }

    public Experiment(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.region = region;
        this.num_of_trials = num_of_trials;
        this.geoLocation = geoLocation;
        this.published = published;

        questions = new ArrayList<>();
        experimenters = new ArrayList<>();
        published = true;
        ended = false;
    }

    /**
     * This publish the experiment to the public.
     */
    public void publish() {
        this.published = true;
    }

    /**
     * This unpublish the experiment.
     */
    public void unpublish() {
        this.published = false;
    }

    /**
     * This ends the experiment.
     */
    public void end() {
        this.ended = true;
    }

    /**
     * This adds a user who subscribed the experiment to the list
     *
     * @param user This is a candidate user to add
     */
    public void subscribe(User user) {
        experimenters.add(user);
    }

    // getters and setters

    /**
     * This returns a owner of the experiment
     *
     * @return Return the owner user
     */
    @Exclude
    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    /**
     * This returns a name of the experiment
     *
     * @return Return the name
     */
    public String getName() {
        return name;
    }

    /**
     * This returns a description of the experiment
     *
     * @return Return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This sets a description of the experiment
     *
     * @param description This is a description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This returns a region of the experiment
     *
     * @return Return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * This sets a region of the experiment
     *
     * @param region This is a region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * This returns a minimum number of trials requirements of the experiment
     *
     * @return Return the minimum number of trials.
     */
    public int getNum_of_trials() {
        return num_of_trials;
    }

    /**
     * This sets a region of the experiment
     *
     * @param num_of_trials This is a minimum number of trials requirements to set
     */
    public void setNum_of_trials(int num_of_trials) {
        this.num_of_trials = num_of_trials;
    }

    /**
     * This returns whether geoLocation requirements for the experiment is enabled or not
     *
     * @return Return true or false
     */
    public boolean getGeoLocation() {
        return geoLocation;
    }

    /**
     * This sets a geoLocation requirements of the experiment
     *
     * @param geoLocation This is a geoLocation requirements to set
     */
    public void setGeoLocation(Boolean geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * This returns whether the experiment is published or not
     *
     * @return Return published or not
     */
    public boolean isPublished() {
        return published;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public abstract String getType();

    @Override
    public int compareTo(Experiment experiment) {
        return this.getType().compareTo(experiment.getType());
    }
}
