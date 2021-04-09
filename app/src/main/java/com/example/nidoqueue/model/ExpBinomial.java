package com.example.nidoqueue.model;

import java.util.ArrayList;

/**
 * Classname:   ExpBinomial.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Handles the Binomial aspect of the experiments.
 * Issues:      Non-functional, planning stages.
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

    public void increasePass() {
        pass += 1;
        trials.add(new Trial((double) this.getPass(), "p" + Integer.toString(this.getPass())));
    }

    public void increaseFail() {
        fail += 1;
        trials.add(new Trial((double) this.getPass(), "f" + Integer.toString(this.getFail())));
    }

    public int getPass() {
        return pass;
    }

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
}
