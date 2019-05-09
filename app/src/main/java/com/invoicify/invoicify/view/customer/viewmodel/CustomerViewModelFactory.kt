package com.invoicify.invoicify.view.customer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.customer.usecase.GetCustomersListUseCase
import javax.inject.Inject

class CustomerViewModelFactory @Inject constructor(
        private val getCustomersListUseCase: GetCustomersListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(getCustomersListUseCase) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }


}