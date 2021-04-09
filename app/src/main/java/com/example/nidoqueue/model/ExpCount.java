package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname:   ExpCount.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles the Count aspect of the experiments.
 * Issues:      Non-functional, planning stages.
 */
public class ExpCount extends Experiment {

    private int count;
    private ArrayList<Trial> trials;
    final private String TYPE = "count";

    public ExpCount() {

    }

    public ExpCount(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);
        count = 0;
        this.trials = new ArrayList<>();
    }

    private void increaseCount() {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        count += 1;
        trials.add(new Trial((double) this.getCount(), Integer.toString(this.getCount())));
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
