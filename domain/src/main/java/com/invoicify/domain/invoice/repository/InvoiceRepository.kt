package com.invoicify.domain.invoice.repository

import com.invoicify.domain.invoice.model.InvoiceModel
import io.reactivex.Completable
import io.reactivex.Observable


interface InvoiceRepository {


    fun getInvoice(path: HashMap<String, String>): Observable<List<InvoiceModel>>

    fun newInvoice(map: HashMap<String, Any>): Completable

    fun updateInvoice(map: HashMap<String, Any>): Completable


}