package com.br.vini.compose.api.response

import com.br.vini.compose.api.model.Usuario

class UsuarioResponseBody(
    var _id: String = "",
    var nome: String = "",
    var email: String = "",
    var foto: String? = null
)