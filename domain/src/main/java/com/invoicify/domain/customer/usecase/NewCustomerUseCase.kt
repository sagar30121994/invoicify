package com.invoicify.domain.customer.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.customer.repository.CustomerRepository
import io.reactivex.Completable

class NewCustomerUseCase(private val repository: CustomerRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(t: HashMap<String, Any>): Completable {
        return repository.addNewCustomer(t)
    }
}