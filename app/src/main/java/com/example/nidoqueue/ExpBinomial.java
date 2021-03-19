package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpBinomial extends Experiment {
    private int pass;
    private int fail;
    private final String TYPE = "binomial";
    private ArrayList<Trial> trials;

    public ExpBinomial(User owner, String name, String description, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        pass = 0;
        fail = 0;
        this.trials = new ArrayList<>();
    }

    public void increasePass() {
        pass += 1;
        trials.add(new Trial((double) this.getPass(), "p" + Integer.toString(this.getPass())));
    }

    public void increaseFail() {
        fail += 1;
        trials.add(new Trial((double) this.getPass(), "f" + Integer.toString(this.getFail())));
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

    public ArrayList<Trial> getTrials() {
        return trials;
    }
}
