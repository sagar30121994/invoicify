package com.invoicify.invoicify.view.orderproduct.viewmodel

import androidx.lifecycle.ViewModel
import com.invoicify.domain.products.model.ProductModel
import com.invoicify.domain.products.usecase.AddNewProductUseCase
import com.invoicify.domain.products.usecase.GetProductUseCase
import com.invoicify.domain.products.usecase.UpdateProductUseCase
import io.reactivex.Completable
import io.reactivex.Observable

class OrderProductViewModel(private val newProductUseCase: AddNewProductUseCase,
                            private val updateProductUseCase: UpdateProductUseCase,
                            private val getProductUseCase: GetProductUseCase) : ViewModel() {

    fun addNewProduct(t: HashMap<String, Any>): Completable {
        return newProductUseCase.execute(t)
    }

    fun updateProductInfo(t: HashMap<String, Any>) {
        updateProductUseCase.execute(t)
    }

    fun validateDetails(productModel: ProductModel): Observable<Int> {
        return Observable.create { emitter ->

            if (productModel != null) {

                if (!productModel.item_name.matches(Regex("([a-z A-Z]){3,}"))) {
                    emitter.onNext(1)
                }
                if (productModel.item_prize <= 0.0) {
                    emitter.onNext(2)
                }
                if (productModel.item_stock <= 0) {
                    emitter.onNext(3)
                }
                if (productModel.low_quantity <= 0) {
                    emitter.onNext(4)
                }
                if (productModel.cgst != null && productModel.cgst <= -0.1) {
                    emitter.onNext(5)
                }
                if (productModel.sgst != null && productModel.sgst <= -0.1) {
                    emitter.onNext(6)
                }
                if (productModel.igst != null && productModel.igst <= -0.1) {
                    emitter.onNext(7)
                }

            } else {
                emitter.onNext(91)
            }

        }

    }

    fun getAllProducts(accountKey: String): Observable<MutableMap<String, Any>> {
        return getProductUseCase.execute(accountKey)
    }
}