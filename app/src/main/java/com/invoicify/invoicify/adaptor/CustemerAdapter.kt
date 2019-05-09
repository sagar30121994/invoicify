package com.invoicify.invoicify.adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.invoicify.invoicify.R

class CustemerAdapter(val items: ArrayList<String>, val context: Activity) : RecyclerView.Adapter<CustemerAdapter.CustemerViewHolder>() {
    override fun onBindViewHolder(holder: CustemerViewHolder, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustemerViewHolder {
        return CustemerViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_customers_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }


    class CustemerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

    }

}

