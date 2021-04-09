package com.example.nidoqueue.model;

import java.util.ArrayList;


/**
 * Classname:   ExpNonNegative.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles the Non-Negative aspect of the experiments.
 * Issues:      Non-functional, planning stages.
 */
public class ExpNonNegative extends Experiment{
    private ArrayList<Integer> trials;
    private ArrayList<Trial> trial;
    final private String TYPE = "nonNegative";

    public ExpNonNegative() {

    }

    public ExpNonNegative(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);
        trials = new ArrayList<>();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void addTrial(int count){
        trials.add(count);
        trial.add(new Trial((double)count, Integer.toString(this.trial.size())));
    }

    public ArrayList<Integer> getTrials() {
        return trials;
    }

    public ArrayList<Trial> getTrial() {
        return trial;
    }
}
