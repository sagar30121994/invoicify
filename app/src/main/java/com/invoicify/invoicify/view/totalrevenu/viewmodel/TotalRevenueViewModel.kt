package com.invoicify.invoicify.view.totalrevenu.viewmodel

import androidx.lifecycle.ViewModel
import com.invoicify.domain.invoice.usecase.GetInvoiceUseCase

class TotalRevenueViewModel(private val getInvoiceUseCase: GetInvoiceUseCase) : ViewModel() {

    fun getAllInvoice(t: HashMap<String, String>) {
        getInvoiceUseCase.execute(t)

    }
}