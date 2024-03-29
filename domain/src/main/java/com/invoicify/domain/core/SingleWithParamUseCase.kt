package com.invoicify.domain.core

import io.reactivex.Single


interface SingleWithParamUseCase<in T, R> {

    fun execute(t: T): Single<R>
}