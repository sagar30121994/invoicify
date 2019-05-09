package com.invoicify.domain.purchase.model

import com.invoicify.domain.customer.model.CustomerModel
import com.invoicify.domain.invoice.model.ItemDetails
import com.invoicify.domain.user.model.UserModel

data class PurchaseModel(

        var invoiceId: String = "",
        var createdAt: String = "",
        var dueDate: String = "",
        var orderItems: HashMap<String, ItemDetails> = HashMap<String, ItemDetails>(),
        var subtoatl: Double = 0.0,
        var total_tax: Double = 0.0,
        var grand_total: Double = 0.0,
        var discount_applied: String = "1%",
        var discount_value: Double = 0.0,
        var customerDetails: HashMap<String, CustomerModel> = HashMap<String, CustomerModel>(),
        var OrnigzationDetails: HashMap<String, UserModel> = HashMap<String, UserModel>()

)