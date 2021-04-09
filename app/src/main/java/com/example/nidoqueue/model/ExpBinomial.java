package com.example.nidoqueue.model;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Classname:   ExpBinomial.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Handles the Binomial aspect of the experiments.
 */
public class ExpBinomial extends Experiment {
    private int pass;
    private int fail;
    private final String TYPE = "binomial";
    private ArrayList<Trial> trials;

    public ExpBinomial(){}

    public ExpBinomial(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);

        pass = 0;
        fail = 0;
        trials = new ArrayList<>();
    }
    @Override
    public void increasePass() {
        pass += 1;
        trials.add(new Trial((double) this.getPass(), "p" + Integer.toString(this.getPass())));
    }
    @Override
    public void increaseFail() {
        fail += 1;
        trials.add(new Trial((double) this.getPass(), "f" + Integer.toString(this.getFail())));
    }
    @Override
    public int getPass() {
        return pass;
    }
    @Override
    public int getFail() {
        return fail;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    @Override
    public void increaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
    }
    public void decreaseCount(int count) {
    }
    @Override
    public int getCount() {
        return total_count;
    }

    @Override
    public void addTrial(double measurement){ }
}
