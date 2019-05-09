package com.invoicify.domain.home.model

data class Last3InvoicesModel(
        val last3Invoice: HashMap<String, HashMap<String, String>> = HashMap<String, HashMap<String, String>>()
)