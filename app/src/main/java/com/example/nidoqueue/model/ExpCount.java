package com.example.nidoqueue.model;

public class ExpCount extends Experiment{

    private int count;
    final private String TYPE = "count";

    public ExpCount(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        count = 0;
    }

    private void increaseCount(){
        // I have this designed to be used with a button that is pressed to increment the value by one
        // we could have it take a specific number input from the user but I think that would be confusing and cumbersome to the user
        count += 1;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
