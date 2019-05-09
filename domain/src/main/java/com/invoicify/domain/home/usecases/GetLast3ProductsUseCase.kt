package com.invoicify.domain.home.usecases

import com.invoicify.domain.core.SingleWithParamUseCase
import com.invoicify.domain.home.model.Last3ProductsWithLowquantity
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Single

class GetLast3ProductsUseCase(private val repository: HomeRepository) : SingleWithParamUseCase<HashMap<String, String>, Last3ProductsWithLowquantity> {
    override fun execute(t: HashMap<String, String>): Single<Last3ProductsWithLowquantity> {
        return repository.getLast3Products(t)
    }
}