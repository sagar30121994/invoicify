package com.invoicify.domain.products.model

data class ProductModel(
        var item_name: String = "",
        var item_stock: Int = 0,
        var item_unit: String = "",
        var item_tax_type: Boolean = true,
        var cgst: Double = 0.0,
        var sgst: Double = 0.0,
        var igst: Double = 0.0,
        var item_prize: Double = 0.0,
        var low_quantity: Int = 0
)