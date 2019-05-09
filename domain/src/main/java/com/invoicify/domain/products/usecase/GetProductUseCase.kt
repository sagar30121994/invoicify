package com.invoicify.domain.products.usecase

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.products.repository.ProductRepository
import io.reactivex.Observable

class GetProductUseCase(private val repository: ProductRepository) : ObservableWithParamUseCase<String, MutableMap<String, Any>> {
    override fun execute(T: String): Observable<MutableMap<String, Any>> {
        return repository.getProduct(T)
    }
}