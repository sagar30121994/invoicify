package com.invoicify.domain.home.usecases

import com.invoicify.domain.core.SingleWithParamUseCase
import com.invoicify.domain.home.model.Last3InvoicesModel
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Single

class GetLast3InvoicesUseCase(private val repository: HomeRepository) : SingleWithParamUseCase<HashMap<String, String>, Last3InvoicesModel> {
    override fun execute(t: HashMap<String, String>): Single<Last3InvoicesModel> {
        return repository.getLast3Invoices(t)
    }
}