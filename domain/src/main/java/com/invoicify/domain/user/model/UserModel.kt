package com.invoicify.domain.user.model

data class UserModel(

        var companyName: String = "",
        var gstn: String = "",
        var establishedDate: String = "",
        var bussiness_Email: String = "",
        var Contact: String = "",
        val Address: HashMap<String, String> = HashMap<String, String>(),
        var SignatureURL: String = "",
        val Admins: HashMap<String, HashMap<String, String>> = HashMap<String, HashMap<String, String>>(),
        val Subscription: HashMap<String, String> = HashMap<String, String>(),
        var LogoURL: String = ""

)