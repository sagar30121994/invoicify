package com.invoicify.invoicify.view.customer.viewmodel

import androidx.lifecycle.ViewModel;
import com.invoicify.domain.customer.usecase.GetCustomersListUseCase

class CustomerViewModel(private val getCustomersListUseCase: GetCustomersListUseCase) : ViewModel() {

    fun getAllCustomers(t: String) {
        getCustomersListUseCase.execute(t)
    }

}
