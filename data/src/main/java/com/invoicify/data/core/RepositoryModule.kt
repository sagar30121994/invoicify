package com.invoicify.data.core

import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {


    @Provides
    fun providesMessagesRepository(repository: String): String {
        return repository
    }
}