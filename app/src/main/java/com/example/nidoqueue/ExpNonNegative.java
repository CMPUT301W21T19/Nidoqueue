package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpNonNegative extends Experiment{
    private ArrayList<Integer> trials;
    private ArrayList<Trial> trial;
    final private String TYPE = "nonNegative";

    public ExpNonNegative(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
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
