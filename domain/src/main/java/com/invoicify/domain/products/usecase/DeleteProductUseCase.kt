package com.invoicify.domain.products.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.products.repository.ProductRepository
import io.reactivex.Completable

class DeleteProductUseCase(private val repository: ProductRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(t: HashMap<String, Any>): Completable {
        return repository.deleteProduct(t)
    }
}