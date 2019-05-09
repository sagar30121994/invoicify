package com.invoicify.domain.user.repository

import com.invoicify.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun getUserDetails(documentPath: String): Single<UserModel>

    fun setUserDetails(userModel: UserModel): Completable

}