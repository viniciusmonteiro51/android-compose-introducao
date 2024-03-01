package com.br.vini.compose.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import javax.inject.Singleton
import android.content.Context

const val SETTINGS = "settings"
@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun providerPreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
       return PreferenceDataStoreFactory.create(
           produceFile = {context.preferencesDataStoreFile(SETTINGS)}
       )
    }
}