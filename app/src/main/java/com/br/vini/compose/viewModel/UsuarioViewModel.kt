package com.br.vini.compose.viewModel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.br.vini.compose.api.ApiRepository
import com.br.vini.compose.api.ApiState
import com.br.vini.compose.api.request.UsuarioPutRequestBody
import com.br.vini.compose.api.response.UsuarioPutResponseBody
import com.br.vini.compose.api.response.UsuarioResponseBody
import com.br.vini.compose.api.response.UsuariosResponseBody
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val application: Application
) : AndroidViewModel(application) {
    private val apiRepository = ApiRepository()

    private val _usuariosResponseBody = mutableStateOf<ApiState<UsuariosResponseBody>>(ApiState.Created())
    val usuariosResponseBody: State<ApiState<UsuariosResponseBody>> = _usuariosResponseBody

    fun getUsuarios() {
        viewModelScope.launch {
            _usuariosResponseBody.value = ApiState.Loading()
            _usuariosResponseBody.value = apiRepository.getUsuarios()
        }
    }

    private val _usuarioResponseBody = mutableStateOf<ApiState<UsuarioResponseBody>>(ApiState.Created())
    val usuarioResponseBody: State<ApiState<UsuarioResponseBody>> = _usuarioResponseBody

    fun getUsuario(id: String) {
        viewModelScope.launch {
            _usuarioResponseBody.value = ApiState.Loading()
            _usuarioResponseBody.value = apiRepository.getUsuarioID(id)
        }
    }

    private val _usuarioPutResponseBody = mutableStateOf<ApiState<UsuarioPutResponseBody>>(ApiState.Created())
    val usuarioPutResponseBody: State<ApiState<UsuarioPutResponseBody>> = _usuarioPutResponseBody

    fun putUsuario(id: String, requestBody: UsuarioPutRequestBody) {
        viewModelScope.launch {
            _usuarioPutResponseBody.value = ApiState.Loading()
            _usuarioPutResponseBody.value = apiRepository.putUsuario(id, requestBody)
        }
    }
}