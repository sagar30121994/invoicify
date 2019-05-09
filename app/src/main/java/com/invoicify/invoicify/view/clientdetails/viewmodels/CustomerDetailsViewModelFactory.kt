package com.invoicify.invoicify.view.clientdetails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.customer.usecase.NewCustomerUseCase
import com.invoicify.domain.customer.usecase.UpdateCustomerUseCase
import javax.inject.Inject

class CustomerDetailsViewModelFactory @Inject constructor
(
        private val newCustomerUseCase: NewCustomerUseCase,
        private val updateCustomerUseCase: UpdateCustomerUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerDetailsViewModel::class.java)) {
            return CustomerDetailsViewModel(newCustomerUseCase, updateCustomerUseCase) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}