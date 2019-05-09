package com.invoicify.invoicify.core

import com.invoicify.data.home.HomeRepositoryImpl
import com.invoicify.data.products.ProductRepositoryImpl
import com.invoicify.data.user.UserDetailsRepositoryImpl
import com.invoicify.domain.home.usecases.*
import com.invoicify.domain.products.usecase.AddNewProductUseCase
import com.invoicify.domain.products.usecase.GetProductUseCase
import com.invoicify.domain.products.usecase.UpdateProductUseCase
import com.invoicify.domain.user.usecases.GetUserDetailsUseCase
import com.invoicify.domain.user.usecases.SetUserDetailsUseCase
import com.invoicify.invoicify.view.analysis.viewmodel.AnalysisViewModelFactory
import com.invoicify.invoicify.view.bussinessdetails.viewmodel.BussinessViewModelFctory
import com.invoicify.invoicify.view.orderproduct.viewmodel.OrderProductViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesUserDetailsFactory(repository: UserDetailsRepositoryImpl): BussinessViewModelFctory {
        return BussinessViewModelFctory(
                GetUserDetailsUseCase(repository),
                SetUserDetailsUseCase(repository)

        )
    }

    @Provides
    fun provideHomeDetailsFactory(repository: HomeRepositoryImpl): AnalysisViewModelFactory {
        return AnalysisViewModelFactory(
                GetThisMonthsTotalRevenueUseCase(repository),
                GetTodaysSoFarDataUseCase(repository),
                GetLast3InvoicesUseCase(repository),
                GetLast3CustomersUseCase(repository),
                GetLast3ProductsUseCase(repository)
        )
    }


    @Provides
    fun provideOrderProductFactory(repository: ProductRepositoryImpl): OrderProductViewModelFactory {
        return OrderProductViewModelFactory(AddNewProductUseCase(repository),
                UpdateProductUseCase(repository), GetProductUseCase(repository))

    }


}