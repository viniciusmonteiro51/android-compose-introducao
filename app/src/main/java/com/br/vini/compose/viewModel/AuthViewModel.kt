package com.br.vini.compose.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.vini.compose.api.ApiEndpoint
import com.br.vini.compose.api.request.LoginRequestBody
import com.br.vini.compose.api.response.LoginResponseBody
import com.br.vini.compose.api.response.UsuariosResponseBody
import com.br.vini.compose.datastore.AppDataStore
import com.br.vini.compose.datastore.AppDataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appDataStore: AppDataStore
) : ViewModel() {
    var autenticado = mutableStateOf(false)
    val loading = mutableStateOf(false)

    fun login(
        user: String,
        senha: String,
        onSucess: () -> Unit,
        onError: (String) -> Unit
    ) {

        if (user.isEmpty()) {
            onError("Informe o usuário")
            return
        }

        if (senha.isEmpty()) {
            onError("Informe a senha")
            return
        }

        val requestBody = LoginRequestBody()
        requestBody.email = user
        requestBody.senha = senha

        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://api-estudos.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoint = retrofit.create(Endpoint::class.java)

        viewModelScope.launch {
            loading.value = true

            val callback = endpoint.login(requestBody)

            callback.enqueue(object : Callback<LoginResponseBody> {
                override fun onFailure(call: Call<LoginResponseBody>, t: Throwable) {
                    if (t.message.isNullOrBlank()) {
                        onError("No fail message avaliable")
                    } else {
                        onError(t.message!!)
                    }
                }
                    override fun onResponse(call: Call<LoginResponseBody>, response: Response<LoginResponseBody>) {
                        onSucess()

                        if (response.isSuccessful) {
                            println(response.body()!!.usuario.nome)
                            println(response.body()!!.token)
                        } else {

                        }
                    }

            })

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

interface Endpoint {
    @POST("/login")
    fun login(@Body requestBody: LoginRequestBody) : Call<LoginResponseBody>
}