package com.invoicify.data.sharepreference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject constructor(var context: Context) : PreferencesHelper {
    override fun setUserType(userType: String) {
        init()
        mPrefs.edit().putString(PREF_NAME, userType)
    }


    lateinit var mPrefs: SharedPreferences

    override fun getUserType(): String {
        init()
        return mPrefs.getString(USERTYPE, "none")

    }

    private fun init() {
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    companion object {
        var PREF_NAME = "Invoicify_share_pref"
        var USERTYPE = "User_Type"
    }

}