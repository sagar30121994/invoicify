package com.invoicify.domain.core

import io.reactivex.Observable

interface ObservableWithParamUseCase<in T, R> {

    fun execute(t: T): Observable<R>

}