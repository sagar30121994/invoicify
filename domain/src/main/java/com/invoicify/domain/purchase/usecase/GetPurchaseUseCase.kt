package com.invoicify.domain.purchase.usecase

import com.invoicify.domain.core.ObservableUseCase
import com.invoicify.domain.purchase.model.PurchaseModel
import com.invoicify.domain.purchase.repository.PurchaseRepository
import io.reactivex.Observable

class GetPurchaseUseCase(private val repository: PurchaseRepository) : ObservableUseCase<List<PurchaseModel>> {
    override fun execute(T: String): Observable<List<PurchaseModel>> {
        return repository.getPurchase(T)
    }
}