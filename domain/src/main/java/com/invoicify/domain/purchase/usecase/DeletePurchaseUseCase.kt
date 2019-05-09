package com.invoicify.domain.purchase.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.purchase.repository.PurchaseRepository
import io.reactivex.Completable

class DeletePurchaseUseCase(private val repository: PurchaseRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(t: HashMap<String, Any>): Completable {
        return repository.deletePurchase(t)
    }
}