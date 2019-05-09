package com.invoicify.invoicify.view.totalrevenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.invoice.usecase.GetInvoiceUseCase
import javax.inject.Inject

class TotalRevenueViewModelFactory @Inject constructor(
        private val getInvoiceUseCase: GetInvoiceUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TotalRevenueViewModel::class.java)) {
            return TotalRevenueViewModel(getInvoiceUseCase) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}