package com.example.nidoqueue;

public class ExpBinomial extends Experiment {
    private int pass;
    private int fail;
    private final String TYPE = "binomial";

    public ExpBinomial(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        pass = 0;
        fail = 0;
    }

    public void increasePass() {
        pass += 1;
    }

    public void increaseFail() {
        fail += 1;
    }

    public int getPass() {
        return pass;
    }

    public int getFail() {
        return fail;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
