package com.invoicify.data.sharepreference

interface PreferencesHelper {
    fun getUserType(): String
    fun setUserType(userType: String)

}