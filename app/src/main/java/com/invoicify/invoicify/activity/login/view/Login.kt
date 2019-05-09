package com.invoicify.invoicify.activity.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.invoicify.invoicify.R
import com.invoicify.invoicify.view.auth.fragment.LoginFragment

class Login : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener {
    override fun isAlreadyLogin(flag: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseAnalytics.getInstance(this)


    }
}
