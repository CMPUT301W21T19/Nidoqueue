package com.example.nidoqueue.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nidoqueue.R;

import java.util.ArrayList;
/**
 * This method of setting up the custom list was used from the CMPUT301 labs with credit going to the TA's.
 */
public class ExperimentList extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> experiments;
    private Context context;
    public ExperimentList(Context context, ArrayList<Experiment> experiments){
        super(context, 0, experiments);
        this.experiments = experiments;
        this.context = context;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            //view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }
        /******************************************************************************
         * Dead Code --- Dead Code --- Dead Code
         ******************************************************************************/
        /**
         * An experiment object is created using the position that was passed in by the "getView" method.
         * These TextView variables are used to display the views in the content.xml window.
         */
        /**
        Experiment exp = experiments.get(position);
        TextView Owner = view.findViewById(R.id.info_text);
        TextView Name = view.findViewById(R.id.year_text);
        TextView Info = view.findViewById(R.id.month_text);
        TextView Region = view.findViewById(R.id.day_text);
        TextView NumTrials = view.findViewById(R.id.day_text);
        TextView GeoLoc = view.findViewById(R.id.day_text);
        TextView Published = view.findViewById(R.id.day_text);
        //Owner.setText(exp.getOwner()); // Retrieves the info from get methods found in the experiment class
        Name.setText(exp.getName());
        Info.setText(exp.getDescription());
        Region.setText(exp.getRegion());
        NumTrials.setText(exp.getRegion());
        GeoLoc.setText(exp.getRegion());
        Published.setText(exp.getRegion());
         */
        return view;

    }

}