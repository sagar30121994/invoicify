package com.invoicify.domain.core

import io.reactivex.Completable


interface CompletableUseCase {

    fun execute(): Completable
}