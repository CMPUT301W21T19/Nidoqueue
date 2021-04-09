package com.example.nidoqueue.activity;

import android.view.View;
/**
 * Classname:   RecyclerViewClickListener.java
 * Version:     Final
 * Date:        April 9th, 2021
 * Purpose:     Interface that handles the parameters for a view and the position in the view.
 */
interface RecyclerViewClickListener {
    void recyclerViewListClicked(View v, int position);
}
