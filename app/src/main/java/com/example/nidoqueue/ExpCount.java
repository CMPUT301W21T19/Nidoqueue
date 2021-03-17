package com.example.nidoqueue;

public class ExpCount extends Experiment{

    private int count;

    public ExpCount(User owner, String description) {
        super(owner, description);
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
