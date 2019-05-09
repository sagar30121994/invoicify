package com.invoicify.domain.customer.model

data class CustomerModel(
        var name: String = "",
        var gstn: String = "",
        var personal_mail: String = "",
        var contact_no: String = "",
        var addressLine1: String = "",
        var addressLine2: String = "",
        var city: String = "",
        var state: String = "",
        var pin: String = ""

)