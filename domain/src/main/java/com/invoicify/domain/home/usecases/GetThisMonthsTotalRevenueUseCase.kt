package com.invoicify.domain.home.usecases

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.home.model.ThisMonthsTotalModel
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Observable

class GetThisMonthsTotalRevenueUseCase(private val repository: HomeRepository) : ObservableWithParamUseCase<String, ThisMonthsTotalModel> {
    override fun execute(t: String): Observable<ThisMonthsTotalModel> {
        return repository.getThidMonthsTotal(t)
    }


}