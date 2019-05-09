package com.invoicify.domain.customer.usecase

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.customer.model.CustomerModel
import com.invoicify.domain.customer.repository.CustomerRepository
import io.reactivex.Observable

class GetCustomersListUseCase(private val repository: CustomerRepository) : ObservableWithParamUseCase<String, List<CustomerModel>> {
    override fun execute(T: String): Observable<List<CustomerModel>> {
        return repository.getCustomers(T)
    }
}