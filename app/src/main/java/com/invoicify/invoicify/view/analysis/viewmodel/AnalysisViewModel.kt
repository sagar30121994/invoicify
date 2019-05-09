package com.invoicify.invoicify.view.analysis.viewmodel

import androidx.lifecycle.ViewModel
import com.invoicify.domain.home.usecases.*

class AnalysisViewModel(
        private val getTodaysSoFarDataUseCase: GetTodaysSoFarDataUseCase,
        private val getThisMonthsTotalRevenueUseCase: GetThisMonthsTotalRevenueUseCase,
        private val getLast3InvoicesUseCase: GetLast3InvoicesUseCase,
        private val getLast3CustomersUseCase: GetLast3CustomersUseCase,
        private val getLast3Products: GetLast3ProductsUseCase) : ViewModel() {

    fun getThisMonthsRevenue(t: String) = getThisMonthsTotalRevenueUseCase.execute(t)

    fun getTodysSoFar(t: HashMap<String, String>) = getTodaysSoFarDataUseCase.execute(t)

    fun getLast3Invoices(t: HashMap<String, String>) = getLast3InvoicesUseCase.execute(t)

    fun getLast3Customers(t: HashMap<String, String>) = getLast3CustomersUseCase.execute(t)

    fun getLast3Products(t: HashMap<String, String>) = getLast3Products.execute(t)

}