package com.invoicify.invoicify.view.orderproduct.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invoicify.domain.products.usecase.AddNewProductUseCase
import com.invoicify.domain.products.usecase.GetProductUseCase
import com.invoicify.domain.products.usecase.UpdateProductUseCase
import javax.inject.Inject

class OrderProductViewModelFactory @Inject constructor(
        private val newProductUseCase: AddNewProductUseCase,
        private val updateProductUseCase: UpdateProductUseCase,
        private val getProductUseCase: GetProductUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderProductViewModel::class.java)) {
            return OrderProductViewModel(newProductUseCase, updateProductUseCase, getProductUseCase) as T
        }
        throw  IllegalArgumentException("Unknown Viewmodel class")
    }
}