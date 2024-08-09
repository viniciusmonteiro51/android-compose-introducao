package com.br.vini.compose.api.request

data class UsuarioPutRequestBody(
    var nome: String = "",
    var email: String = "",
    var foto: String? = null
)