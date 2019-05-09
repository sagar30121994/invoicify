package com.invoicify.domain.invoice.usecase

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.invoice.model.InvoiceModel
import com.invoicify.domain.invoice.repository.InvoiceRepository
import io.reactivex.Observable

class GetInvoiceUseCase(private val repository: InvoiceRepository) : ObservableWithParamUseCase<HashMap<String, String>, List<InvoiceModel>> {
    override fun execute(t: HashMap<String, String>): Observable<List<InvoiceModel>> {
        return repository.getInvoice(t)
    }


}