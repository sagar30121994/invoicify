package com.invoicify.domain.purchase.repository

import com.invoicify.domain.purchase.model.PurchaseModel
import io.reactivex.Completable
import io.reactivex.Observable

interface PurchaseRepository {

    fun addNewPurchase(map: HashMap<String, Any>): Completable

    fun updatePurchase(map: HashMap<String, Any>): Completable

    fun getPurchase(path: String): Observable<List<PurchaseModel>>

    fun deletePurchase(map: HashMap<String, Any>): Completable

}