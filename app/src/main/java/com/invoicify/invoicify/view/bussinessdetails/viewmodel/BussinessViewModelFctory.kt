package com.invoicify.invoicify.view.bussinessdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.user.usecases.GetUserDetailsUseCase
import com.invoicify.domain.user.usecases.SetUserDetailsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BussinessViewModelFctory @Inject constructor(
        private val getUserDetailsUseCase: GetUserDetailsUseCase,
        private val setUserDetailsUseCase: SetUserDetailsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BussinessViewModel::class.java)) {
            return BussinessViewModel(getUserDetailsUseCase, setUserDetailsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}