package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpNonNegative extends Experiment{
//    private ArrayList<Integer> trials;
    final private String TYPE = "nonNegative";
    private ArrayList<Trial> trials;

    public ExpNonNegative(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        trials = new ArrayList<>();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void addTrial(int count){
//        trials.add(count);
        trials.add(new Trial((double)count, Integer.toString(this.trials.size())));
    }

//    public ArrayList<Integer> getTrials() {
//        return trials;
//    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }
}
