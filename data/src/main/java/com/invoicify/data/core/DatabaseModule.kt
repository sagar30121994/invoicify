package com.invoicify.data.core

import com.google.firebase.firestore.FirebaseFirestore
import com.invoicify.data.sharepreference.AppPreferencesHelper
import com.invoicify.data.sharepreference.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()


    @Singleton
    @Provides
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }



}