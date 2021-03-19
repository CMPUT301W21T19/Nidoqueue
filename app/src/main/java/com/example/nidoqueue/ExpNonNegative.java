package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpNonNegative extends Experiment{
    private ArrayList<Integer> trials;
    final private String TYPE = "nonNegative";

    public ExpNonNegative(User owner, String description) {
        super(owner, description);
        trials = new ArrayList<>();
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
