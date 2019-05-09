package com.invoicify.domain.home.usecases

import com.invoicify.domain.core.SingleWithParamUseCase
import com.invoicify.domain.home.model.Last3Customers
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Single

class GetLast3CustomersUseCase(private val repository: HomeRepository) : SingleWithParamUseCase<HashMap<String, String>, Last3Customers> {

    override fun execute(t: HashMap<String, String>): Single<Last3Customers> {
        return repository.getLast3Customers(t)

    }
}