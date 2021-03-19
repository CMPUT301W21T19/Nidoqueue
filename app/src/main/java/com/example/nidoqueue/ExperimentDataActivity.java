package com.example.nidoqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;

import java.text.DecimalFormat;

public class ExperimentDataActivity extends AppCompatActivity {

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

    private View.OnClickListener Home = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_data);
        Intent intent = getIntent();
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
