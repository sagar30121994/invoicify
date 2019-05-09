package com.invoicify.invoicify.adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.invoicify.invoicify.R

class InvoiceOrderItemAdapter(val items: ArrayList<String>, val context: Activity) : RecyclerView.Adapter<InvoiceOrderItemAdapter.InvoiceOrderIemViewHolder>() {
    override fun onBindViewHolder(holder: InvoiceOrderIemViewHolder, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceOrderIemViewHolder {
        return InvoiceOrderIemViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_invoice_order_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }


    class InvoiceOrderIemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

    }

}

