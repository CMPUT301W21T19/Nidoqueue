package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ExperimentDataActivity extends AbstractActivity {

    /**
     * Classname:   ExperimentManager.java
     * Version:     Prototype
     * Date:        March 19th, 2021
     * Purpose:     This handles experiment transitions similar to the UserControl class.
     * Issues:      Incomplete, will be worked on in the future.
     */


    // NOTES:   I have complete faith that the calculations performed in DataCalc will work.
    //          Unfortunately, I have been unable to test the experiment detection features and
    //          how the code will respond to different experiment types.

    private Experiment experiment;
    private DataCalc calc;

    private TextView quartilesView;
    private TextView meanView;
    private TextView medianView;
    private TextView stDevView;
    private ImageButton homeBtn;
    private ImageButton backBtn;
    private GraphView histogram;
    private GraphView plots;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_data);
        Intent intent = getIntent();
        // NEED TO GET EXPERIMENT FROM PREVIOUS ACTIVITY


        calc = new DataCalc(experiment);

        quartilesView = findViewById(R.id.exp_stat_quartiles);
        meanView = findViewById(R.id.exp_stat_mean);
        medianView = findViewById(R.id.exp_stat_median);
        stDevView = findViewById(R.id.exp_stat_stDev);
        homeBtn = findViewById(R.id.home_button3);
        backBtn = findViewById(R.id.back_button6);
        histogram = findViewById(R.id.histogram);
        plots = findViewById(R.id.plots);

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

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestManager.Home();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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


}