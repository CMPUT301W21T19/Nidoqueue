package com.example.nidoqueue.model;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Classname:   ExpCount.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Handles the Count aspect of the experiments.
 */
public class ExpCount extends Experiment {

    private ArrayList<Trial> trials;
    final private String TYPE = "count";

    public ExpCount() {

    }

    public ExpCount(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);

        total_count = 0;
        this.trials = new ArrayList<>();
    }

    @Override
    public void increaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        total_count += count;
        trials.add(new Trial((double) this.getCount(), Integer.toString(this.getCount())));
    }
    public void decreaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        total_count -= count;
        trials.add(new Trial((double) this.getCount(), Integer.toString(this.getCount())));
    }

    @Override
    public int getCount() {
        return total_count;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void addTrial(double measurement){ }
    @Override
    public void increasePass() { }
    @Override
    public void increaseFail() { }
    @Override
    public int getPass() {
        return 0;
    }
    @Override
    public int getFail() {
        return 0;
    }
}
