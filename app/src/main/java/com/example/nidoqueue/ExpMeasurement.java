package com.example.nidoqueue;

import java.util.ArrayList;

public class ExpMeasurement extends Experiment{
    // since measurements require units, I think we should consider using a custom ArrayList or something similar
    // doing this may make it easier to display.
    // the reason I didn't just implement this is that it may make certain things that I cannot foresee more difficult

    private String unit;
    private ArrayList<Double> trials;
    final private String TYPE = "measurement";

    public ExpMeasurement(User owner, String name, String description, String unit, Boolean geoLocation) {
        super(owner, name, description, geoLocation);
        this.unit = unit;
        this.trials = new ArrayList<>();
    }

    public void addTrial(double measurement){
        trials.add(measurement);
    }

    public ArrayList<Double> getTrials() {
        return trials;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
