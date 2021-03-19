package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpNonNegative extends Experiment{
    private ArrayList<Integer> trials;

    public ExpNonNegative(User owner, String description) {
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
