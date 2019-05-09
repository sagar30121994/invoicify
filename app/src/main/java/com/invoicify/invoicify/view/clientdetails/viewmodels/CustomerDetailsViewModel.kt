package com.invoicify.invoicify.view.clientdetails.viewmodels

import androidx.lifecycle.ViewModel
import com.invoicify.domain.customer.usecase.NewCustomerUseCase
import com.invoicify.domain.customer.usecase.UpdateCustomerUseCase

class CustomerDetailsViewModel
(
        private val newCustomerUseCase: NewCustomerUseCase,
        private val updateCustomerUseCase: UpdateCustomerUseCase

) : ViewModel() {

    fun addNewCustomer(t: HashMap<String, Any>) {
        newCustomerUseCase.execute(t)
    }

    fun updateCustomerDetails(t: HashMap<String, Any>) {
        updateCustomerUseCase.execute(t)
    }

}