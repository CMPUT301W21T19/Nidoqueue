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
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Adapter for recyclerview that shows list of experiments
 */
public class ExpListAdapter extends RecyclerView.Adapter<ExpListAdapter.Vh> {
    public ArrayList<Experiment> list;
    private RecyclerViewClickListener ExpClickListener;

    public ExpListAdapter(ArrayList<Experiment> list, RecyclerViewClickListener ExpClickListener) {
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


    class Vh extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;

        public Vh(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv = itemView.findViewById(R.id.exp_list_name);
        }
        @Override
        public void onClick(View v) {
            ExpClickListener.recyclerViewListClicked(v, this.getLayoutPosition());

        }
    }

}