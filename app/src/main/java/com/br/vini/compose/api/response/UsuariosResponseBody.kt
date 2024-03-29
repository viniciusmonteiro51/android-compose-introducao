package com.br.vini.compose.api.response

import com.br.vini.compose.api.model.Usuario

data class UsuariosResponseBody(
    var docs: List<Usuario>
)