package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpCount extends Experiment {

    private int count;
    private ArrayList<Trial> trials;
    final private String TYPE = "count";

    public ExpCount(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        count = 0;
        this.trials = new ArrayList<>();
    }

    private void increaseCount() {
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        count += 1;
        trials.add(new Trial((double) this.getCount(), Integer.toString(this.getCount())));
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
