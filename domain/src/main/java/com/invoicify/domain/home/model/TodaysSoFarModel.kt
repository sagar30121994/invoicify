package com.invoicify.domain.home.model

data class TodaysSoFarModel(
        val invoices: ArrayList<HashMap<String, String>> = ArrayList<HashMap<String, String>>()
)