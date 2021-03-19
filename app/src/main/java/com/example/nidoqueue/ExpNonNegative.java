package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpNonNegative extends Experiment{
//    private ArrayList<Integer> trials;
    final private String TYPE = "nonNegative";
    private ArrayList<Trial> trials;
    private ArrayList<Integer> data;

    public ExpNonNegative(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        this.trials = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void addTrial(int count){
        data.add(count);
        trials.add(new Trial((double)count, Integer.toString(this.trials.size())));
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }
}
