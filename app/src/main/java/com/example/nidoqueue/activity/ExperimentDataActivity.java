package com.example.nidoqueue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DataCalc;
import com.example.nidoqueue.model.ExpBinomial;
import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.ExpMeasurement;
import com.example.nidoqueue.model.ExpNonNegative;
import com.example.nidoqueue.model.Experiment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;

import java.text.DecimalFormat;
import com.example.nidoqueue.R;

/**
 * Classname:   ExperimentDataActivity.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Activity that will handle the experiment data, using OnClickListeners.
 * Issues:      Unable to take in data. Needs to be tested.
 */
public class ExperimentDataActivity extends AbstractActivity {
    RequestManager requestManager = RequestManager.getInstance();
    ContextManager contextManager = ContextManager.getInstance();
    private Experiment experiment;
    private DataCalc calc;

    private TextView quartilesView;
    private TextView meanView;
    private TextView medianView;
    private TextView stDevView;
    private ImageButton homeBtn, backBtn;
    private Button resultsBtn;
    private GraphView histogram;
    private GraphView plots;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(SignInActivity.class);
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ExperimentActivity.class);

        }
    };
    private View.OnClickListener Results = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.transition(ExperimentActivity.class);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_data);
        Intent intent = getIntent();
        experiment = requestManager.getExperiment();
        calc = new DataCalc(experiment);

        quartilesView = findViewById(R.id.exp_stat_quartiles);
        meanView = findViewById(R.id.exp_stat_mean);
        medianView = findViewById(R.id.exp_stat_median);
        stDevView = findViewById(R.id.exp_stat_stDev);
        homeBtn = findViewById(R.id.home_button3);
        backBtn = findViewById(R.id.back_button6);
        resultsBtn = findViewById(R.id.results_button);
        histogram = findViewById(R.id.histogram);
        plots = findViewById(R.id.plots);

        homeBtn.setOnClickListener(Home);
        backBtn.setOnClickListener(Back);
        resultsBtn.setOnClickListener(Results);

        histogram.setTitle("Histogram");
        plots.setTitle("Change over Time");

        if(experiment instanceof ExpCount){
            countDisplay((ExpCount) experiment);
        }else if(experiment instanceof ExpBinomial){
            binomialDisplay((ExpBinomial) experiment);
        }else if(experiment instanceof ExpNonNegative){
            nonNegDisplay((ExpNonNegative) experiment);
        }else if(experiment instanceof ExpMeasurement){
            measureDisplay((ExpMeasurement) experiment);
        }

    }

    private void countDisplay(ExpCount count){
        String medianString = decimalFormat.format(calc.getMedian());
        medianView.setText(medianString);

        double[] quartiles = calc.getQuartiles();
        String quartileString = decimalFormat.format(quartiles[0]) + ", " + decimalFormat.format(quartiles[1]) + ", " + decimalFormat.format(quartiles[2]);
        quartilesView.setText(quartileString);

        String meanString = decimalFormat.format(calc.getMean());
        meanView.setText(meanString);

        String stDevString = decimalFormat.format(calc.getStDev());
        stDevView.setText(stDevString);

        histogram.addSeries(calc.getHistogram());

        plots.addSeries(calc.getPlot());
    }

    private void binomialDisplay(ExpBinomial binomial){
        String medianString = decimalFormat.format(calc.getMedian());
        medianView.setText(medianString);

        double[] quartiles = calc.getQuartiles();
        String quartileString = decimalFormat.format(quartiles[0]) + ", " + decimalFormat.format(quartiles[1]) + ", " + decimalFormat.format(quartiles[2]);
        quartilesView.setText(quartileString);

        String meanString = decimalFormat.format(calc.getMean());
        meanView.setText(meanString);

        String stDevString = decimalFormat.format(calc.getStDev());
        stDevView.setText(stDevString);

        histogram.addSeries(calc.getHistogram());

        plots.addSeries(calc.getPlotPass());
        plots.addSeries(calc.getPlotFail());
    }

    private void nonNegDisplay(ExpNonNegative nonNeg){
        String medianString = decimalFormat.format(calc.getMedian());
        medianView.setText(medianString);

        double[] quartiles = calc.getQuartiles();
        String quartileString = decimalFormat.format(quartiles[0]) + ", " + decimalFormat.format(quartiles[1]) + ", " + decimalFormat.format(quartiles[2]);
        quartilesView.setText(quartileString);

        String meanString = decimalFormat.format(calc.getMean());
        meanView.setText(meanString);

        String stDevString = decimalFormat.format(calc.getStDev());
        stDevView.setText(stDevString);

        histogram.addSeries(calc.getHistogram());

        plots.addSeries(calc.getPlot());
    }

    private void measureDisplay(ExpMeasurement measure){
        String medianString = decimalFormat.format(calc.getMedian());
        medianView.setText(medianString);

        double[] quartiles = calc.getQuartiles();
        String quartileString = decimalFormat.format(quartiles[0]) + ", " + decimalFormat.format(quartiles[1]) + ", " + decimalFormat.format(quartiles[2]);
        quartilesView.setText(quartileString);

        String meanString = decimalFormat.format(calc.getMean());
        meanView.setText(meanString);

        String stDevString = decimalFormat.format(calc.getStDev());
        stDevView.setText(stDevString);

        histogram.addSeries(calc.getHistogram());

        plots.addSeries(calc.getPlot());
    }


    @Override
    public FirebaseFirestore getDB() {
        return null;
    }
}
