package com.invoicify.invoicify.view.invoice.viewmodels

import androidx.lifecycle.ViewModel
import com.invoicify.domain.invoice.usecase.NewInvoiceUseCase
import com.invoicify.domain.invoice.usecase.UpdateInvoiceUseCase

class NewInvoiceViewModel(private val setNewInvoiceUseCase: NewInvoiceUseCase,
                          private val updateInvoiceUseCase: UpdateInvoiceUseCase) : ViewModel() {

    fun setNewInvoice(map: HashMap<String, Any>) {
        setNewInvoiceUseCase.execute(map)
    }


    fun updateExistingInvoice(map: HashMap<String, Any>) {
        updateInvoiceUseCase.execute(map)
    }

}