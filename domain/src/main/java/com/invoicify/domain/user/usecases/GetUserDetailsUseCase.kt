package com.invoicify.domain.user.usecases

import com.invoicify.domain.core.SingleWithParamUseCase
import com.invoicify.domain.user.model.UserModel
import com.invoicify.domain.user.repository.UserRepository
import io.reactivex.Single

class GetUserDetailsUseCase(private val repository: UserRepository) : SingleWithParamUseCase<String, UserModel> {

    override fun execute(documentPath: String): Single<UserModel> {
        return repository.getUserDetails(documentPath)
    }

}