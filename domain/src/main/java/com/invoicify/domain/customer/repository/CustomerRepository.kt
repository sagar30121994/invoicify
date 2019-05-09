package com.invoicify.domain.customer.repository

import com.invoicify.domain.customer.model.CustomerModel
import io.reactivex.Completable
import io.reactivex.Observable

interface CustomerRepository {

    fun addNewCustomer(hashmap: HashMap<String, Any>): Completable

    fun updateCustomer(hashmap: HashMap<String, Any>): Completable

    fun getCustomers(path: String): Observable<List<CustomerModel>>
}