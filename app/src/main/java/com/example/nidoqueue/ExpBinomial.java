package com.example.nidoqueue;

public class ExpBinomial extends Experiment {
    private int pass;
    private int fail;

    public ExpBinomial(User owner, String description) {
        super(owner, description);
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
}
