package com.example.nidoqueue.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nidoqueue.R;
import com.example.nidoqueue.model.Experiment;

import java.util.ArrayList;

/**
 * Classname:   ExpListAdapter.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Adapter for recyclerview that shows list of experiments
 * Issues:      None
 */
public class ExpListAdapter extends RecyclerView.Adapter<ExpListAdapter.Vh> {
    public ArrayList<Experiment> list;
    private View.OnClickListener ExpClickListener;

    public ExpListAdapter(ArrayList<Experiment> list, View.OnClickListener ExpClickListener) {
        this.list = list;
        this.ExpClickListener = ExpClickListener;
    }




    @NonNull
    @Override

    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.experiment_list, parent, false);
        Vh Holder = new Vh(view);
        Holder.itemView.setOnClickListener(ExpClickListener);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Vh extends RecyclerView.ViewHolder {
        TextView tv;

        public Vh(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(ExpClickListener);
            tv = itemView.findViewById(R.id.exp_list_name);
        }
    }

}