package com.example.nidoqueue.model;

import java.util.ArrayList;

import static java.lang.String.valueOf;


/**
 * Classname:   ExpNonNegative.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Handles the Non-Negative aspect of the experiments.
 */
public class ExpNonNegative extends Experiment{

    final private String TYPE = "nonNegative";

    public ExpNonNegative() {

    }

    public ExpNonNegative(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);
    }

    @Override
    public String getType() {
        return TYPE;
    }



    public ArrayList<Integer> getTrials() {
        return int_trials;
    }

    public ArrayList<Trial> getTrial() {
        return trial;
    }
    @Override
    public void increaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        int_trials.add(count);
        total_count += count;
        trial.add(new Trial((double)count, Integer.toString(this.trial.size())));
    }
    public void decreaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        if (total_count > 0) {
            total_count -= count;
        }
    }
    @Override
    public int getCount() {
        return total_count;
    }
    @Override
    public void addTrial(double measurement) { }
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
