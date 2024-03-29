package com.br.vini.compose.api.response

import com.br.vini.compose.api.model.Usuario

data class LoginResponseBody (
    var token: String= "",
    var usuario: Usuario
)