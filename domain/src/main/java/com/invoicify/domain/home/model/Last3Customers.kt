package com.invoicify.domain.home.model

data class Last3Customers(
        val last3Customers: HashMap<String, HashMap<String, String>> = HashMap<String, HashMap<String, String>>()

)