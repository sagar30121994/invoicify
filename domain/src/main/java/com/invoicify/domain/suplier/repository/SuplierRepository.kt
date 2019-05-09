package com.invoicify.domain.suplier.repository

import com.invoicify.domain.suplier.model.SuplierModel
import io.reactivex.Completable
import io.reactivex.Observable

interface SuplierRepository {

    fun addNewSuplier(map: HashMap<String, Any>): Completable

    fun updateSuplier(map: HashMap<String, Any>): Completable

    fun getSupliers(t: String): Observable<List<SuplierModel>>

}