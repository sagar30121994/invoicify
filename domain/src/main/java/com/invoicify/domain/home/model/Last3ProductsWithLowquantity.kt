package com.invoicify.domain.home.model

data class Last3ProductsWithLowquantity(
        val products: HashMap<String, HashMap<String, String>> = HashMap<String, HashMap<String, String>>()
)