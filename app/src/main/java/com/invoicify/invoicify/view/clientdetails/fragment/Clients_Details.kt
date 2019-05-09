package com.invoicify.invoicify.view.clientdetails.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.invoicify.invoicify.R
import com.invoicify.invoicify.interfacefragment.FragmentInteraction


class Client_Details : Fragment() {


    internal var revenueFragmentInteractor: FragmentInteraction? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteraction) {
            revenueFragmentInteractor = context

            revenueFragmentInteractor!!.onAttached(R.string.add_client_fragment)

        } else {
            throw RuntimeException("$context must implement revenueFragmentInteractor")
        }
    }

    override fun onDetach() {
        super.onDetach()
        revenueFragmentInteractor!!.onDettached(R.string.add_client_fragment)
        revenueFragmentInteractor = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client__details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}// Required empty public constructor
