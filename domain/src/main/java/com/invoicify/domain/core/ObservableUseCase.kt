package com.invoicify.domain.core

import io.reactivex.Observable


interface ObservableUseCase<T> {

    fun execute(T: String): Observable<T>

}