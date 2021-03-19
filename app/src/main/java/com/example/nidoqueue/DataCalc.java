package com.example.nidoqueue;

import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class DataCalc {
    private Experiment experiment;
    private Calculator calculator;

    public DataCalc(Experiment experiment) {
        this.experiment = experiment;

        if(this.experiment instanceof ExpCount){
            this.calculator = new CountCalc((ExpCount) experiment);
        }else if(experiment instanceof ExpBinomial){
            this.calculator = new BinomialCalc((ExpBinomial) experiment);
        }else if(experiment instanceof ExpNonNegative){
            this.calculator = new NonNegCalc((ExpNonNegative) experiment);
        }else if(experiment instanceof ExpMeasurement){
            this.calculator = new MeasureCalc((ExpMeasurement) experiment);
        }
    }

    public double getMedian(){
        return calculator.getMedian();
    }
    public double[] getQuartiles(){
        return calculator.getQuartiles();
    }
    public double getMean(){
        return calculator.getMean();
    }
    public double getStDev(){
        return calculator.getStDev();
    }
    public BarGraphSeries<DataPoint> getHistogram(){
        return calculator.getHistogram();
    }
    public LineGraphSeries<DataPoint> getPlot(){
        if(experiment instanceof ExpBinomial){
            return null;
        }
        return calculator.getPlot();
    }
    public LineGraphSeries<DataPoint> getPlotPass(){
        if(experiment instanceof ExpBinomial){
            return calculator.getPlotPass();
        }
        return null;
    }
    public LineGraphSeries<DataPoint> getPlotFail(){
        if(experiment instanceof ExpBinomial){
            return calculator.getPlotFail();
        }
        return null;
    }

}

abstract class Calculator{
    public abstract double getMedian();
    public abstract double[] getQuartiles();
    public abstract double getMean();
    public abstract double getStDev();
    public abstract BarGraphSeries<DataPoint> getHistogram();
    public abstract LineGraphSeries<DataPoint> getPlot();
    public abstract LineGraphSeries<DataPoint> getPlotPass();
    public abstract LineGraphSeries<DataPoint> getPlotFail();
}
class CountCalc extends Calculator{
    private ExpCount count;

    public CountCalc(ExpCount count) {
        this.count = count;
    }

    public double getMedian(){
        return median();
    }
    public double[] getQuartiles(){
        return quartiles();
    }
    public double getMean(){
        return mean();
    }
    public double getStDev(){
        return stDev();
    }
    public BarGraphSeries<DataPoint> getHistogram(){
        return histogramSeries();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LineGraphSeries<DataPoint> getPlot(){
        return plotSeries();
    }

    @Override
    public LineGraphSeries<DataPoint> getPlotPass() {
        return null;
    }
    @Override
    public LineGraphSeries<DataPoint> getPlotFail() {
        return null;
    }


    private double median(){
        return (double) -1;
    }
    private double[] quartiles(){
        double[] Q = {-1,-1,-1};
        return Q;
    }
    private double mean(){
        return (double) -1;
    }
    private double stDev(){
        return (double) -1;
    }
    private BarGraphSeries<DataPoint> histogramSeries(){
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, count.getCount())
        });
        return series;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LineGraphSeries<DataPoint> plotSeries(){
        ArrayList<Trial> trials = count.getTrials();
        int graphMax = trials.size();
        DataPoint[] dataPoints = new DataPoint[graphMax];
        for (int i = 0; i < graphMax; i++){
            ZonedDateTime zonedDateTime = trials.get(i).getZonedDateTime();
            long y = zonedDateTime.toEpochSecond();
            int x = (int) trials.get(i).getData();
            dataPoints[i] = new DataPoint(y, x);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        return series;
    }
}
class BinomialCalc extends Calculator{
    private ExpBinomial binomial;

    public BinomialCalc(ExpBinomial binomial) {
        this.binomial = binomial;
    }

    public double getMedian(){
        return median();
    }
    public double[] getQuartiles(){
        return quartiles();
    }
    public double getMean(){
        return mean();
    }
    public double getStDev(){
        return stDev();
    }
    public BarGraphSeries<DataPoint> getHistogram(){
        return histogramSeries();
    }

    @Override
    public LineGraphSeries<DataPoint> getPlot() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LineGraphSeries<DataPoint> getPlotPass(){
        return plotSeriesPass();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LineGraphSeries<DataPoint> getPlotFail(){
        return plotSeriesFail();
    }

    private double median(){
        return (double) -1;
    }
    private double[] quartiles(){
        double[] Q = {-1,-1,-1};
        return Q;
    }
    private double mean(){
        return (double) -1;
    }
    private double stDev(){
        return (double) -1;
    }
    private BarGraphSeries<DataPoint> histogramSeries(){
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, binomial.getPass()),
                new DataPoint(1, binomial.getFail())
        });
        return series;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LineGraphSeries<DataPoint> plotSeriesPass(){
        ArrayList<Trial> trials = binomial.getTrials();
        ArrayList<Trial> passes;
        passes = new ArrayList<>();
        for(int i = 0; i < trials.size(); i++){
            if(trials.get(i).getId().contains("p")){
                passes.add(trials.get(i));
            }
        }
        DataPoint[] passPoints = new DataPoint[passes.size()];
        int max = passes.size();

        for (int i = 0; i < max; i++) {
            ZonedDateTime zonedDateTime = passes.get(i).getZonedDateTime();
            long y = zonedDateTime.toEpochSecond();
            int x = (int) passes.get(i).getData();
            passPoints[i] = new DataPoint(y, x);
        }

        LineGraphSeries<DataPoint> passSeries = new LineGraphSeries<>(passPoints);
        return passSeries;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LineGraphSeries<DataPoint> plotSeriesFail(){
        ArrayList<Trial> trials = binomial.getTrials();
        ArrayList<Trial> fails;
        fails = new ArrayList<>();
        for(int i = 0; i < trials.size(); i++){
            if(trials.get(i).getId().contains("p")){
                fails.add(trials.get(i));
            }
        }
        DataPoint[] failPoints = new DataPoint[fails.size()];
        int max = fails.size();

        for (int i = 0; i < max; i++) {
            ZonedDateTime zonedDateTime = fails.get(i).getZonedDateTime();
            long y = zonedDateTime.toEpochSecond();
            int x = (int) fails.get(i).getData();
            failPoints[i] = new DataPoint(y, x);
        }

        LineGraphSeries<DataPoint> failSeries = new LineGraphSeries<>(failPoints);
        return failSeries;
    }
}
class NonNegCalc extends Calculator{
    private ExpNonNegative nonNeg;
    private ArrayList<Double> classData;
    private ArrayList<Trial> trials;

    public NonNegCalc(ExpNonNegative nonNeg) {
        this.nonNeg = nonNeg;
        this.trials = nonNeg.getTrials();
        this.classData = new ArrayList<>();

        for(int i = 0; i < trials.size(); i++){
            classData.add(trials.get(i).getData());
        }
        Collections.sort(classData);
    }

    public double getMedian(){
        return median(classData);
    }
    public double[] getQuartiles(){
        return quartiles(classData);
    }
    public double getMean(){
        return mean(classData);
    }
    public double getStDev(){
        return stDev(classData);
    }
    public BarGraphSeries<DataPoint> getHistogram(){
        return histogramSeries();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LineGraphSeries<DataPoint> getPlot(){
        return plotSeries();
    }

    @Override
    public LineGraphSeries<DataPoint> getPlotPass() {
        return null;
    }

    @Override
    public LineGraphSeries<DataPoint> getPlotFail() {
        return null;
    }

    private double median(ArrayList<Double> data){
        double median;
        if (data.size() % 2 == 1){
            median = data.get(data.size()/2);
        }else{
            int pos1 = (data.size()/2)-1;
            int pos2 = (data.size()/2);
            double num1 = data.get(pos1);
            double num2 = data.get(pos2);
            median = (num1 + num2) / 2;
        }
        return median;
    }
    private double[] quartiles(ArrayList<Double> data){
        double q1, q2, q3;

        // Q2
        q2 = median(data);
        // Q1
        ArrayList<Double> firstHalf = new ArrayList<>();
        for(int i = 0; i < data.size()/2; i++){
            firstHalf.add(data.get(i));
        }
        q1 = median(firstHalf);
        // Q3
        ArrayList<Double> lastHalf = new ArrayList<>();
        for(int i = (int) Math.ceil((float)data.size()/2); i < data.size(); i++){
            lastHalf.add(data.get(i));
        }
        q3 = median(lastHalf);

        double[] quartiles = {q1, q2, q3};
        return quartiles;

    }
    private double mean(ArrayList<Double> data){
        double sum = 0;
        for(int i = 0; i < data.size(); i++){
            sum += data.get(i);
        }
        double mean = sum / data.size();
        return mean;
    }
    private double stDev(ArrayList<Double> data){
        // Based off of Programiz example code
        // https://www.programiz.com/java-programming/examples/standard-deviation
        double sd = 0.0;
        double mean = mean(data);
        for(double num: data){
            sd += Math.pow(num - mean, 2);
        }
        return (float)Math.sqrt(sd/data.size());
    }
    private BarGraphSeries<DataPoint> histogramSeries() {
        // calculates frequency of number occurrence
        ArrayList<DataPoint> workingData = new ArrayList<>();
        for(int i = 0; i < classData.size(); i++){
            double x = classData.get(i);
            int y = 1;
            while(classData.get(i+1) == x){
                y++;
                i++;
            }
            workingData.add(new DataPoint(x,y));
        }
        DataPoint[] dataPoints = new DataPoint[workingData.size()];
        for(int i = 0; i < trials.size(); i++){
            dataPoints[i] = workingData.get(i);
        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
        return series;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LineGraphSeries<DataPoint> plotSeries(){
        int graphMax = trials.size();
        DataPoint[] dataPoints = new DataPoint[graphMax];
        for(int i = 0; i < graphMax; i++){
            ZonedDateTime zonedDateTime = trials.get(i).getZonedDateTime();
            long y = zonedDateTime.toEpochSecond();
            int x = (int) trials.get(i).getData();
            dataPoints[i] = new DataPoint(y, x);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        return series;
    }
}
class MeasureCalc extends Calculator{
    private ExpMeasurement measure;
    private ArrayList<Double> classData;
    private ArrayList<Trial> trials;

    public MeasureCalc(ExpMeasurement measure) {
        this.measure = measure;
        this.trials = measure.getTrials();
        this.classData = new ArrayList<>();

        for(int i = 0; i < trials.size(); i++){
            classData.add(trials.get(i).getData());
        }
        Collections.sort(classData);
    }

    public double getMedian(){
        return median(classData);
    }
    public double[] getQuartiles(){
        return quartiles(classData);
    }
    public double getMean(){
        return mean(classData);
    }
    public double getStDev(){
        return stDev(classData);
    }
    public BarGraphSeries<DataPoint> getHistogram(){
        return histogramSeries();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LineGraphSeries<DataPoint> getPlot(){
        return plotSeries();
    }

    @Override
    public LineGraphSeries<DataPoint> getPlotPass() {
        return null;
    }

    @Override
    public LineGraphSeries<DataPoint> getPlotFail() {
        return null;
    }

    private double median(ArrayList<Double> data){
        double median;
        if (data.size() % 2 == 1){
            median = data.get(data.size()/2);
        }else{
            int pos1 = (data.size()/2)-1;
            int pos2 = (data.size()/2);
            double num1 = data.get(pos1);
            double num2 = data.get(pos2);
            median = (num1 + num2) / 2;
        }
        return median;
    }
    private double[] quartiles(ArrayList<Double> data){
        double q1, q2, q3;

        // Q2
        q2 = median(data);
        // Q1
        ArrayList<Double> firstHalf = new ArrayList<>();
        for(int i = 0; i < data.size()/2; i++){
            firstHalf.add(data.get(i));
        }
        q1 = median(firstHalf);
        // Q3
        ArrayList<Double> lastHalf = new ArrayList<>();
        for(int i = (int) Math.ceil((float)data.size()/2); i < data.size(); i++){
            lastHalf.add(data.get(i));
        }
        q3 = median(lastHalf);

        double[] quartiles = {q1, q2, q3};
        return quartiles;

    }
    private double mean(ArrayList<Double> data){
        double sum = 0;
        for(int i = 0; i < data.size(); i++){
            sum += data.get(i);
        }
        double mean = sum / data.size();
        return mean;
    }
    private double stDev(ArrayList<Double> data){
        // Based off of Programiz example code
        // https://www.programiz.com/java-programming/examples/standard-deviation
        double sd = 0.0;
        double mean = mean(data);
        for(double num: data){
            sd += Math.pow(num - mean, 2);
        }
        return (float)Math.sqrt(sd/data.size());
    }
    private BarGraphSeries<DataPoint> histogramSeries() {
        // calculates frequency of number occurrence
        ArrayList<DataPoint> workingData = new ArrayList<>();
        for(int i = 0; i < classData.size(); i++){
            double x = classData.get(i);
            int y = 1;
            while(classData.get(i+1) == x){
                y++;
                i++;
            }
            workingData.add(new DataPoint(x,y));
        }
        DataPoint[] dataPoints = new DataPoint[workingData.size()];
        for(int i = 0; i < trials.size(); i++){
            dataPoints[i] = workingData.get(i);
        }
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
        return series;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LineGraphSeries<DataPoint> plotSeries(){
        int graphMax = trials.size();
        DataPoint[] dataPoints = new DataPoint[graphMax];
        for(int i = 0; i < graphMax; i++){
            ZonedDateTime zonedDateTime = trials.get(i).getZonedDateTime();
            long y = zonedDateTime.toEpochSecond();
            int x = (int) trials.get(i).getData();
            dataPoints[i] = new DataPoint(y, x);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        return series;
    }
}
