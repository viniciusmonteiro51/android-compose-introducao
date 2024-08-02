package com.br.vini.compose.api

import com.br.vini.compose.datastore.AppDataStore
import com.br.vini.compose.datastore.AppDataStoreKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val appDataStore: AppDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val encodedPath = chain.request().url.encodedPath

        if(encodedPath == "/login") {
            val request = chain.request()
            return chain.proceed(request)
        }

        val token = runBlocking { appDataStore.getString(AppDataStoreKeys.TOKEN).first() }
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${token}")
            .build()
        return chain.proceed(request)
    }
}