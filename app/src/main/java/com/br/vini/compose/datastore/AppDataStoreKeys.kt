package com.br.vini.compose.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object AppDataStoreKeys {
    val AUTENTICADO = booleanPreferencesKey("autenticado")
    val TOKEN = stringPreferencesKey("token")
}