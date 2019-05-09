package com.invoicify.invoicify.adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.invoicify.invoicify.R

class InvoiceAdapter(val items: ArrayList<String>, val context: Activity) : RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>() {
    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        return InvoiceViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_invoice_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }


    class InvoiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

    }

}

