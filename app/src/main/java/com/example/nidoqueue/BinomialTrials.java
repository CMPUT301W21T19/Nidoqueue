package com.example.nidoqueue;

public class BinomialTrials extends Experiment{
    private int pass;
    private int fail;

    public BinomialTrials(User owner) {
        super(owner);
        pass = 0;
        fail = 0;
    }

    public void increasePass(){
        pass += 1;
    }

    public void increaseFail(){
        fail += 1;
    }

    public int getPass() {
        return pass;
    }

    public int getFail() {
        return fail;
    }
}
