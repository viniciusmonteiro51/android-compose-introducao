package com.br.vini.compose.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.vini.compose.datastore.AppDataStore
import com.br.vini.compose.datastore.AppDataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appDataStore: AppDataStore
) : ViewModel() {
    var autenticado = mutableStateOf(false)
    val loading = mutableStateOf(false)

    fun login (
        user: String,
        senha: String,
        onSucess: () -> Unit,
        onError:(String) -> Unit
    ) {

        if(user.isEmpty()) {
            onError("Informe o usuário")
            return
        }

        if(senha.isEmpty()) {
            onError("Informe a senha")
            return
        }



        viewModelScope.launch {
            loading.value = true
            delay(2000)
            appDataStore.putBoolean(AppDataStoreKeys.AUTENTICADO, true).apply {
                onSucess()
            }
        }
    }

    fun logOut(
        onLogout: () -> Unit
    ) {
        viewModelScope.launch {
            loading.value = true
            delay(2000)
            appDataStore.putBoolean(AppDataStoreKeys.AUTENTICADO, false).apply {
                onLogout()
            }
        }
    }

}