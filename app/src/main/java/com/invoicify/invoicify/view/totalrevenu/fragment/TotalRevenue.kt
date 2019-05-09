package com.invoicify.invoicify.view.totalrevenu.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.invoicify.invoicify.R
import com.invoicify.invoicify.interfacefragment.FragmentInteraction
import kotlinx.android.synthetic.main.fragment_total_revenue.*

class TotalRevenue : Fragment(), OnChartGestureListener {

    internal var revenueFragmentInteractor: FragmentInteraction? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteraction) {
            revenueFragmentInteractor = context

            revenueFragmentInteractor!!.onAttached(R.string.total_revenue_fragment)

        } else {
            throw RuntimeException("$context must implement revenueFragmentInteractor")
        }
    }

    override fun onDetach() {
        super.onDetach()
        revenueFragmentInteractor!!.onDettached(R.string.total_revenue_fragment)
        revenueFragmentInteractor = null
    }

    lateinit var chart: BarChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_revenue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart = chart1 as BarChart

        initData()

    }


    private fun initData() {

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))

        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        val data = BarData(labels, barDataSet)

        barDataSet.color = ContextCompat.getColor(activity!!, R.color.primaryColor)
        chart.animateY(1000)
        chart.data = data // set the data and list of lables into chart
        chart.data.notifyDataChanged()

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)


    }


    override fun onChartGestureEnd(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartFling(me1: MotionEvent?, me2: MotionEvent?, velocityX: Float, velocityY: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartSingleTapped(me: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartGestureStart(me: MotionEvent?, lastPerformedGesture: ChartTouchListener.ChartGesture?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartScale(me: MotionEvent?, scaleX: Float, scaleY: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartLongPressed(me: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartDoubleTapped(me: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */


}// Required empty public constructor
