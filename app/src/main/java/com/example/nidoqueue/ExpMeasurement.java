package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpMeasurement extends Experiment{
    // since measurements require units, I think we should consider using a custom ArrayList or something similar
    // doing this may make it easier to display.
    // the reason I didn't just implement this is that it may make certain things that I cannot foresee more difficult

    private String unit;
    private ArrayList<Double> trials;

    public ExpMeasurement(User owner, String description, String unit) {
        super(owner, description);
        this.unit = unit;
        this.trials = new ArrayList<>();
    }

    private void addTrial(double measurement){
        trials.add(measurement);
    }

    public ArrayList<Double> getTrials() {
        return trials;
    }
}
