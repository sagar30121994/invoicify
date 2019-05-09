package com.invoicify.invoicify.view.analysis.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.invoicify.data.core.InvoicePreference
import com.invoicify.invoicify.R
import com.invoicify.invoicify.adaptor.CustemerAdapter
import com.invoicify.invoicify.adaptor.InvoiceAdapter
import com.invoicify.invoicify.adaptor.ProductsAdaptor
import com.invoicify.invoicify.core.injector
import com.invoicify.invoicify.interfacefragment.FragmentInteraction
import com.invoicify.invoicify.view.analysis.viewmodel.AnalysisViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_analysis.*


class Analysis : Fragment() {

    internal var revenueFragmentInteractor: FragmentInteraction? = null
    val invoices: ArrayList<String> = ArrayList()


    private val factory = injector.analysisViewModelFactory()
    private lateinit var viewModel: AnalysisViewModel
    private val disposables = CompositeDisposable()
    lateinit var invoicePreference: InvoicePreference
    lateinit var TotalRevenueOfThisMonth: ArrayList<HashMap<String, String>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(bottom_app_bar)

        invoicePreference = InvoicePreference(activity)
        invoicePreference.init()

        addInvoices()

        rv_invoices.layoutManager = LinearLayoutManager(activity)
        rv_invoices.adapter = InvoiceAdapter(invoices, activity)
        (rv_invoices.adapter as InvoiceAdapter).notifyDataSetChanged()

        rv_products.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_products.adapter = ProductsAdaptor(invoices, activity)
        (rv_products.adapter as ProductsAdaptor).notifyDataSetChanged()

        rv_customers.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_customers.adapter = CustemerAdapter(invoices, activity)
        (rv_customers.adapter as CustemerAdapter).notifyDataSetChanged()


        viewModel = ViewModelProviders.of(this, factory).get(AnalysisViewModel::class.java)

        var accountKey = invoicePreference.getAccountKey()

        disposables.add(
                viewModel.getThisMonthsRevenue(accountKey)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            TotalRevenueOfThisMonth = result.metaData
                            total.text = TotalRevenueOfThisMonth[0].get("total_amount")
                        }, { t: Throwable? ->
                            Log.e("error", t!!.localizedMessage + "   " + accountKey)

                        })
        )




        cl_rev.setOnClickListener {
            view.findNavController().navigate(R.id.action_total_revenue)
        }


    }

    override fun onDetach() {
        super.onDetach()
        revenueFragmentInteractor!!.onDettached(R.string.analytics_fragment)
        revenueFragmentInteractor = null
    }


    private fun addInvoices() {

        invoices.add("one")
        invoices.add("two")
        invoices.add("three")
        invoices.add("four")
        invoices.add("five")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteraction) {
            revenueFragmentInteractor = context

            revenueFragmentInteractor!!.onAttached(R.string.analytics_fragment)

        } else {
            throw RuntimeException("$context must implement revenueFragmentInteractor")
        }
    }

}// Required empty public constructor