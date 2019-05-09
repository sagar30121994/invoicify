package com.invoicify.domain.home.usecases

import com.invoicify.domain.core.ObservableWithParamUseCase
import com.invoicify.domain.home.model.TodaysSoFarModel
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Observable

class GetTodaysSoFarDataUseCase(private val repository: HomeRepository) : ObservableWithParamUseCase<HashMap<String, String>, TodaysSoFarModel> {
    override fun execute(t: HashMap<String, String>): Observable<TodaysSoFarModel> {
        return repository.getToadysSoFar(t)
    }
}