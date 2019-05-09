package com.invoicify.domain.products.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.products.repository.ProductRepository
import io.reactivex.Completable

class AddNewProductUseCase(private val repository: ProductRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(hashMap: HashMap<String, Any>): Completable {
        return repository.addNewProduct(hashMap)
    }
}