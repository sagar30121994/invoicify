package com.invoicify.domain.user.usecases

import com.invoicify.domain.core.CompletableWithParamUseCase
import com.invoicify.domain.user.model.UserModel
import com.invoicify.domain.user.repository.UserRepository
import io.reactivex.Completable

class SetUserDetailsUseCase(private val repository: UserRepository) : CompletableWithParamUseCase<UserModel> {
    override fun execute(t: UserModel): Completable {
        return repository.setUserDetails(t)
    }


}