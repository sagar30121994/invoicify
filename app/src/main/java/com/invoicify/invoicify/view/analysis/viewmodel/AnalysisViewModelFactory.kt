package com.invoicify.invoicify.view.analysis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.home.usecases.*
import javax.inject.Inject

class AnalysisViewModelFactory @Inject constructor(
        private val getThisMonthsTotalRevenueUseCase: GetThisMonthsTotalRevenueUseCase,
        private val getTodaysSoFarDataUseCase: GetTodaysSoFarDataUseCase,
        private val getLast3InvoicesUseCase: GetLast3InvoicesUseCase,
        private val getLast3CustomersUseCase: GetLast3CustomersUseCase,
        private val getLast3Products: GetLast3ProductsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AnalysisViewModel::class.java)) {
            return AnalysisViewModel(getTodaysSoFarDataUseCase, getThisMonthsTotalRevenueUseCase, getLast3InvoicesUseCase, getLast3CustomersUseCase, getLast3Products) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}