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
 * Classname:   ExpSearchAdapter.java
 * Version:     Prototype
 * Date:        March 19th, 2021
 * Purpose:     Adapter for recyclerview that shows list of experiments
 * Issues:      None
 */
public class ExpSearchAdapter extends RecyclerView.Adapter<ExpSearchAdapter.Vh> {
    public ArrayList<Experiment> list;
    private RecyclerViewClickListener ExpClickListener;

    public ExpSearchAdapter(ArrayList<Experiment> list, RecyclerViewClickListener ExpClickListener) {
        this.list = list;
        this.ExpClickListener = ExpClickListener;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_experiments_results, parent, false);
        Vh Holder = new Vh(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.owner.setText(list.get(position).getType());
        if(list.get(position).isPublished()) {
            holder.status.setText("Active");
        } else {
            holder.status.setText("Inactive");
        }
        holder.desc.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Vh extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView owner;
        TextView status;
        TextView desc;


        public Vh(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name_results);
            owner = itemView.findViewById(R.id.owner_results);
            status = itemView.findViewById(R.id.status_results);
            desc = itemView.findViewById(R.id.desc_results);
        }

        @Override
        public void onClick(View v) {
            ExpClickListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }

}