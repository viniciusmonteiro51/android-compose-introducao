package com.br.vini.compose.api

import com.br.vini.compose.api.request.LoginRequestBody
import com.br.vini.compose.api.response.LoginResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.br.vini.compose.api.response.UsuariosResponseBody

interface ApiEndpoint {
    @POST("/login")
    suspend fun login(@Body requestBody: LoginRequestBody) : Response<LoginResponseBody>

    @GET("/usuarios")
    suspend fun usuarios() : Response<UsuariosResponseBody>
}