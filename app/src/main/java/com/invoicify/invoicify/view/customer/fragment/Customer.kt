package com.invoicify.invoicify.view.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.invoicify.invoicify.R
import com.invoicify.invoicify.view.customer.viewmodel.CustomerViewModel

class Customer : Fragment() {

    companion object {
        fun newInstance() = Customer()
    }

    private lateinit var viewModel: CustomerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.customer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CustomerViewModel::class.java)

    }

}
