package com.invoicify.domain.invoice.model

data class ItemDetails(
        var item_name: String = "",
        var item_quantity: Int = 0,
        var unit_type: String = "",
        var unit_cost: Float = 0f,
        var cgst: Float = 0f,
        var sgst: Float = 0f,
        var igst: Float = 0f,
        var tax_type: Boolean = true,
        var hsn: Int = 0,
        var discount_applied: Float = 0.0f,
        var discount_value: Double = 0.0,
        var grandtotal: Double = 0.0,
        var subTotal: Double = 0.0
)