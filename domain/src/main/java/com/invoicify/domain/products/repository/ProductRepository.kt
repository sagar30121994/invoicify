package com.invoicify.domain.products.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface ProductRepository {

    fun addNewProduct(hashMap: HashMap<String, Any>): Completable

    fun updateProduct(hashMap: HashMap<String, Any>): Completable

    fun deleteProduct(hashMap: HashMap<String, Any>): Completable

    fun getProduct(t: String): Observable<MutableMap<String, Any>>

}