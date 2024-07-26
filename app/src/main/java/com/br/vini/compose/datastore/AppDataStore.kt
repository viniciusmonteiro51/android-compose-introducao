package com.br.vini.compose.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject


class AppDataStore @Inject constructor(
    private val context: Context

) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SETTINGS)
    }

    suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun putString(key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}