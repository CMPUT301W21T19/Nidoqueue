package com.example.nidoqueue.model;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Classname:   ExpMeasurement.java
 * Version:     Prototype
 * Date:        April 9th, 2021
 * Purpose:     Handles the measurement aspect of the experiments.
 */
public class ExpMeasurement extends Experiment{
    // since measurements require units, I think we should consider using a custom ArrayList or something similar
    // doing this may make it easier to display.
    // the reason I didn't just implement this is that it may make certain things that I cannot foresee more difficult


    final private String TYPE = "measurement";

    public ExpMeasurement() {

    }

    public ExpMeasurement(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published, String unit) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);

        this.unit = unit;
    }

    @Override
    public void addTrial(double measurement){
        measurement_trials.add(measurement);
        trial.add(new Trial(measurement, Integer.toString(this.trial.size())));
    }

    public ArrayList<Double> getTrials() {
        return measurement_trials;
    }

    public String getUnit() {
        return unit;
    }

    public ArrayList<Trial> getTrial() {
        return trial;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void increaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
    }
    public void decreaseCount(int count) {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        total_count -= count;
    }
    @Override
    public int getCount() {
        return total_count;
    }
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
