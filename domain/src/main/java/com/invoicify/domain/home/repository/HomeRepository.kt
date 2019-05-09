package com.invoicify.domain.home.repository

import com.invoicify.domain.home.model.*
import io.reactivex.Observable
import io.reactivex.Single

interface HomeRepository {

    fun getThidMonthsTotal(documentPath: String): Observable<ThisMonthsTotalModel>

    fun getToadysSoFar(documentPath: HashMap<String, String>): Observable<TodaysSoFarModel>

    fun getLast3Invoices(documentPath: HashMap<String, String>): Single<Last3InvoicesModel>

    fun getLast3Customers(documentPath: HashMap<String, String>): Single<Last3Customers>

    fun getLast3Products(documentPath: HashMap<String, String>): Single<Last3ProductsWithLowquantity>

}