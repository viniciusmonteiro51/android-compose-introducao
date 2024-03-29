package com.br.vini.compose.api.request

data class LoginRequestBody (
    var email: String = "",
    var senha: String = ""
)