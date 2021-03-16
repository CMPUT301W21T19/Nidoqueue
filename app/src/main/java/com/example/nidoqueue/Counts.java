package com.example.nidoqueue;

public class Counts extends Experiment{

    private int count;

    public Counts(User owner) {
        super(owner);
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
}
