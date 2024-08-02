package com.br.vini.compose.api

/*
Classe que representa a estrutura de erro definida na API.
Neste caso um campo JSON message.
 */
data class ErrorResponse (
    var message: String = ""
)