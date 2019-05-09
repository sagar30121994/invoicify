package com.invoicify.invoicify.view.invoice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.invoice.usecase.NewInvoiceUseCase
import com.invoicify.domain.invoice.usecase.UpdateInvoiceUseCase
import javax.inject.Inject

class NewInvoiceViewModelFactory @Inject constructor(
        private val setNewInvoiceUseCase: NewInvoiceUseCase,
        private val updateInvoiceUseCase: UpdateInvoiceUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewInvoiceViewModel::class.java)) {
            return NewInvoiceViewModel(setNewInvoiceUseCase, updateInvoiceUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}