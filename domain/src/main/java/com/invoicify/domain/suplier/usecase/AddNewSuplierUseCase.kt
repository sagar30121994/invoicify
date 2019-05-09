package com.invoicify.domain.suplier.usecase

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.suplier.repository.SuplierRepository
import io.reactivex.Completable

class AddNewSuplierUseCase(private val repository: SuplierRepository) : CompletableWithParamUseCase<HashMap<String, Any>> {
    override fun execute(t: HashMap<String, Any>): Completable {
        return repository.addNewSuplier(t)
    }


}