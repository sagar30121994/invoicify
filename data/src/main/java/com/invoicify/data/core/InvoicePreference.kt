package com.invoicify.data.core

import android.content.Context
import android.content.SharedPreferences
import com.invoicify.data.sharepreference.Constants
import com.invoicify.data.sharepreference.Constants.Companion.accountKey


class InvoicePreference(var context: Context) {

    var sharedpreferences: SharedPreferences? = null
    val mypreference = "invoicepref"
    val AdminType = "admintype"

    fun init() {

        if (sharedpreferences == null) {
            sharedpreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE)
        }

    }


    fun setAdminType(adminType: String) {
        init()
        val editor = sharedpreferences?.edit()
        editor?.putString(AdminType, adminType)
        editor?.apply()

    }

    fun getAdmin(): String {
        init()
        return sharedpreferences?.getString(AdminType, "none")!!
    }

    fun setAccountKey(accountKey: String) {
        init()
        val editor = sharedpreferences?.edit()
        editor?.putString(Constants.accountKey, accountKey)
        editor?.apply()
    }


    fun getAccountKey(): String {
        init()
        return sharedpreferences?.getString(accountKey, "none")!!
    }


}