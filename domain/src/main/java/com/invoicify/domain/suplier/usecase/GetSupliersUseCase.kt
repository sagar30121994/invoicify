package com.invoicify.domain.suplier.usecase

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.suplier.model.SuplierModel
import com.invoicify.domain.suplier.repository.SuplierRepository
import io.reactivex.Observable

class GetSupliersUseCase(private val repository: SuplierRepository) : ObservableWithParamUseCase<String, List<SuplierModel>> {

    override fun execute(t: String): Observable<List<SuplierModel>> {
        return repository.getSupliers(t)
    }


}