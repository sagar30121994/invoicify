package com.invoicify.domain.invoice.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.invoice.repository.InvoiceRepository
import io.reactivex.Completable

class UpdateInvoiceUseCase(private val repository: InvoiceRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(t: HashMap<String, Any>): Completable {
        return repository.updateInvoice(t)
    }
}