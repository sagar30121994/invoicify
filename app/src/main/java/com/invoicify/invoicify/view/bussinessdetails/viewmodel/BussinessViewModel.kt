package com.invoicify.invoicify.view.bussinessdetails.viewmodel

import androidx.lifecycle.ViewModel
import com.invoicify.domain.user.model.UserModel
import com.invoicify.domain.user.usecases.GetUserDetailsUseCase
import com.invoicify.domain.user.usecases.SetUserDetailsUseCase

class BussinessViewModel(private val getUserDetailsUseCase: GetUserDetailsUseCase
                         , private val setUserDetailsUseCase: SetUserDetailsUseCase) : ViewModel() {

    fun setUserData(userDetails: UserModel) = setUserDetailsUseCase.execute(userDetails)

    fun getUserDetails(documentPath: String) = getUserDetailsUseCase.execute(documentPath)

}