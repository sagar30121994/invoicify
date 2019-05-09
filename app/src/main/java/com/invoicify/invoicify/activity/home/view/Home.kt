package com.invoicify.invoicify.activity.home.view

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.crashlytics.android.Crashlytics
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
import com.google.android.material.bottomappbar.BottomAppBar.FAB_ALIGNMENT_MODE_END
import com.google.firebase.analytics.FirebaseAnalytics
import com.invoicify.invoicify.R
import com.invoicify.invoicify.interfacefragment.FragmentInteraction
import com.invoicify.invoicify.view.analysis.fragment.Analysis
import com.invoicify.invoicify.view.bottomnavigation.fragment.BottomNavigationFragment
import com.invoicify.invoicify.view.totalrevenu.fragment.TotalRevenue
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_home.*


class Home : AppCompatActivity(), FragmentInteraction {

    override fun onAttached(id: Int) {
        if (id == R.string.total_revenue_fragment) {
            bottom_app_bar.fabAlignmentMode = FAB_ALIGNMENT_MODE_END
            bottom_app_bar.replaceMenu(R.menu.revenue_nevigation_menu)
            bottom_app_bar.animate()
        } else if (id == R.string.analytics_fragment) {
            bottom_app_bar.fabAlignmentMode = FAB_ALIGNMENT_MODE_CENTER
            bottom_app_bar.replaceMenu(R.menu.bottombarmenu)

        } else if (id == R.string.new_invoice_fragment) {


            if (fab.visibility.equals(View.VISIBLE)) {
                fab.visibility = View.INVISIBLE
            }
        } else if (id == R.string.add_client_fragment) {
            if (fab.visibility.equals(View.VISIBLE)) {
                fab.visibility = View.INVISIBLE
            }
        } else if (id == R.string.add_custmers_fragment) {
            if (fab.visibility.equals(View.VISIBLE)) {
                fab.visibility = View.INVISIBLE
            }
        } else if (id == R.string.add_item_fragment) {
            if (fab.visibility.equals(View.VISIBLE)) {
                fab.visibility = View.INVISIBLE
            }
        }

    }

    override fun onDettached(id: Int) {
        if (id == R.string.analytics_fragment) {
        } else if (id == R.string.add_client_fragment) {
        } else if (id == R.string.new_invoice_fragment) {

            if (fab.visibility.equals(View.INVISIBLE)) {
                fab.visibility = View.VISIBLE
            }
        } else if (id == R.string.add_item_fragment) {
        } else if (id == R.string.total_revenue_fragment) {
            bottom_app_bar.fabAlignmentMode = FAB_ALIGNMENT_MODE_CENTER
            bottom_app_bar.replaceMenu(R.menu.bottombarmenu)

        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(bottom_app_bar)
        FirebaseAnalytics.getInstance(this)
        Fabric.with(this, Crashlytics())
        fab.setOnClickListener({ fabSwitch() })


        findNavController(R.id._home_nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            /*         if(destination.id == R.id.) {
                         toolbar.visibility = View.GONE
                         bottomNavigationView.visibility = View.GONE
                     } else {
                     toolbar.visibility = View.VISIBLE
                     bottomNavigationView.visibility = View.VISIBLE
                 }

            */
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottombarmenu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            R.id.app_bar_fav -> toast("fav_clicked")
            R.id.app_bar_search -> toast("search_clicked")
            R.id.app_bar_settings -> toast("settings_clicked")
            R.id.app_create_invoice -> toast("New Something")
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }

        return true
    }

    fun fabSwitch() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id._home_nav_host_fragment)
        navHostFragment!!.getChildFragmentManager().fragments.get(0).let {
            if (it is Analysis) {
                findNavController(R.id._home_nav_host_fragment).navigate(R.id.action_load_new_invoice)

            } else if (it is TotalRevenue) {
                findNavController(R.id._home_nav_host_fragment).navigate(R.id.action_load_invoice)

            }

        }


    }

    fun Context.toast(message: CharSequence) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }
}

