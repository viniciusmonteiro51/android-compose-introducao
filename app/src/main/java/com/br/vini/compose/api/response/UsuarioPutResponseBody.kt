package com.br.vini.compose.api.response

import java.time.LocalDateTime

data class UsuarioPutResponseBody(
    val usuario: UsuarioPut
)

data class UsuarioPut(
    val _id: String,
    val nome: String,
    val email: String,
    val foto: String?,
    val criado_em: LocalDateTime,
    val atualizado_em: LocalDateTime
)