package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname:   ExpMeasurement.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles the measurement aspect of the experiments.
 * Issues:      Non-functional, planning stages.
 */
public class ExpMeasurement extends Experiment{
    // since measurements require units, I think we should consider using a custom ArrayList or something similar
    // doing this may make it easier to display.
    // the reason I didn't just implement this is that it may make certain things that I cannot foresee more difficult

    private String unit;
    private ArrayList<Double> trials;
    private ArrayList<Trial> trial;
    final private String TYPE = "measurement";

    public ExpMeasurement() {

    }

    public ExpMeasurement(User owner, String name, String description, String region, int num_of_trials, boolean geoLocation, boolean published, String unit) {
        super(owner, name, description, region, num_of_trials, geoLocation, published);
        this.unit = unit;
        this.trials = new ArrayList<>();
        this.trial = new ArrayList<>();
    }

    public void addTrial(double measurement){
        trials.add(measurement);
        trial.add(new Trial(measurement, Integer.toString(this.trial.size())));
    }

    public ArrayList<Double> getTrials() {
        return trials;
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
}
