package com.br.vini.compose.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
class AuthViewModel: ViewModel() {
    var autenticado = mutableStateOf(false)

    fun login (
        user: String,
        senha: String,
        onSucess: () -> Unit,
        onError: (String) -> Unit
    ) {

        if(user == "vinissaum" && senha == "1234") {
            onSucess()
            autenticado.value = true
        } else {
            onError("Usuário ou senha inválido")
        }
    }

    fun logOut(
        onSucess: () -> Unit
    ) {
        autenticado.value = false
        onSucess()
    }
}