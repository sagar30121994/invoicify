package com.invoicify.invoicify.core

import com.invoicify.data.core.DatabaseModule
import com.invoicify.data.core.RepositoryModule
import com.invoicify.invoicify.view.analysis.viewmodel.AnalysisViewModelFactory
import com.invoicify.invoicify.view.bussinessdetails.viewmodel.BussinessViewModelFctory
import com.invoicify.invoicify.view.orderproduct.viewmodel.OrderProductViewModelFactory
import dagger.Component
import javax.inject.Singleton


@Component(modules = [
    RepositoryModule::class,
    DatabaseModule::class,
    ViewModelModule::class
])

@Singleton
interface AppComponent {

    fun bussinessViewModelFactory(): BussinessViewModelFctory

    fun analysisViewModelFactory(): AnalysisViewModelFactory

    fun orderProductViewModelFactory(): OrderProductViewModelFactory

}
