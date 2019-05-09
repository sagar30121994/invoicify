package com.invoicify.invoicify.core

import android.app.Application
import com.invoicify.data.core.DatabaseModule

class InvoicifyApp : Application() {


    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppComponent.builder()
                .databaseModule(DatabaseModule())
                .viewModelModule(ViewModelModule())
                .build()
    }


}

lateinit var injector: AppComponent