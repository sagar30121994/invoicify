package com.invoicify.invoicify.utils

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

class SnackBarUtils(var context: Context, var view: View) {

    fun displayMaterialSnackBar(messege: String) {
        val marginBottom = 550
        val snackbar = Snackbar.make(
                view, messege,
                Snackbar.LENGTH_LONG
        )
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams

        params.setMargins(0, 0, 0, marginBottom)
        snackbarView.layoutParams = params
        snackbar.show()
    }
}