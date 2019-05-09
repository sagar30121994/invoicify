package com.invoicify.invoicify.view.invoice.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.invoicify.invoicify.R
import com.invoicify.invoicify.adaptor.InvoiceOrderItemAdapter
import com.invoicify.invoicify.interfacefragment.FragmentInteraction
import kotlinx.android.synthetic.main.fragment_newinvoice.*


class NewInvoiceFragment : Fragment() {


    internal var revenueFragmentInteractor: FragmentInteraction? = null

    val invoices: ArrayList<String> = ArrayList()

    var isItemSelected = false
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteraction) {
            revenueFragmentInteractor = context

            revenueFragmentInteractor!!.onAttached(R.string.new_invoice_fragment)

        } else {
            throw RuntimeException("$context must implement revenueFragmentInteractor")
        }
    }

    override fun onDetach() {
        super.onDetach()
        revenueFragmentInteractor!!.onDettached(R.string.new_invoice_fragment)
        revenueFragmentInteractor = null
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newinvoice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mcv_client_details.setOnClickListener({ view.findNavController().navigate(R.id.action_new_client) })
        var mAdapter = ArrayAdapter<String>(
                activity, // Context
                android.R.layout.simple_dropdown_item_1line, // Layout
                COUNTRIES // Array
        )



        rv_order_item.layoutManager = LinearLayoutManager(activity)
        rv_order_item.adapter = InvoiceOrderItemAdapter(invoices, activity!!)
        (rv_order_item.adapter as InvoiceOrderItemAdapter).notifyDataSetChanged()


        add_new_item.setAdapter(mAdapter)
        add_new_item.threshold = 1

        add_new_item.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
//            Toast.makeText(activity,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
            btn_add_new_item.text = "add"
            isItemSelected = true

        }

        add_new_item.setOnDismissListener {
            Toast.makeText(activity, "Suggestion closed.", Toast.LENGTH_SHORT).show()
            if (!isItemSelected) {
                btn_add_new_item.text = "New Item"
            } else {
                btn_add_new_item.text = "Add Item"
                isItemSelected = false

            }
        }

        btn_add_new_item.setOnClickListener {
            if (btn_add_new_item.text.toString().equals("NEW ITEM", true)) {
                view.findNavController().navigate(R.id.action_new_item)

            } else {
                Toast.makeText(activity, "Else" + add_new_item.text, Toast.LENGTH_SHORT).show()
            }
        }


    }

    var COUNTRIES = arrayOf("Belgium", "France", "Italy", "Germany", "Spain")


}




