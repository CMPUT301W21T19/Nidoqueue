package com.example.nidoqueue;

import java.util.ArrayList;

public class NonNegativeIntegerCounts extends Experiment{
    private ArrayList<Integer> trials;

    public NonNegativeIntegerCounts(User owner, String description) {
        super(owner, description);
        trials = new ArrayList<>();
    }

    public void addTrial(int count){
        trials.add(count);
    }

    public ArrayList<Integer> getTrials() {
        return trials;
    }
}
