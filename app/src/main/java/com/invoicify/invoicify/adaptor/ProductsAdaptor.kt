package com.invoicify.invoicify.adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.invoicify.invoicify.R

class ProductsAdaptor(val items: ArrayList<String>, val context: Activity) : RecyclerView.Adapter<ProductsAdaptor.ProductViewHolder>() {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }


    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

    }

}
