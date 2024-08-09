package com.br.vini.compose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.protobuf.Api
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.br.vini.compose.api.ApiState
import com.br.vini.compose.api.request.UsuarioPutRequestBody
import com.br.vini.compose.viewModel.UsuarioViewModel

@Composable
fun EditarUsuario(
    id: String,
    navController: NavController
) {
    val usuarioViewModel = viewModel<UsuarioViewModel>()
    val usuarioState by usuarioViewModel.usuarioResponseBody
    val usuarioPutState by usuarioViewModel.usuarioPutResponseBody

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(id) {
        usuarioViewModel.getUsuario(id)
    }

    LaunchedEffect(usuarioState) {
        if (usuarioState is ApiState.Success) {
            usuarioState.data?.let { usuario ->
                nome = usuario.nome
                email = usuario.email
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        when (usuarioState) {
            is ApiState.Created -> {}
            is ApiState.Loading -> {
                CircularProgressIndicator()
            }
            is ApiState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = nome,
                        onValueChange = { newValue -> nome = newValue },
                        label = { Text("Nome") },
                        modifier = Modifier
                            .width(350.dp)
                            .padding(top = 10.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { newValue -> email = newValue },
                        label = { Text("Email") },
                        modifier = Modifier
                            .width(350.dp)
                            .padding(top = 10.dp)
                    )
                    Button(
                        onClick = {
                            val requestBody = UsuarioPutRequestBody(nome, email)
                            usuarioViewModel.putUsuario(id, requestBody)
                        },
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .width(350.dp)
                            .height(50.dp)
                    ) {
                        Text(text = "Editar")
                    }
                }
            }
            is ApiState.Error -> {
                usuarioState.message?.let { message ->
                    FailScreen(message)
                }
            }
        }

        when (usuarioPutState) {
            is ApiState.Created -> {}
            is ApiState.Loading -> {
                CircularProgressIndicator()
            }
            is ApiState.Success -> {
                Toast.makeText(context, "Usuário Atualizado", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
            is ApiState.Error -> {
                // Mostrar mensagem de erro
                usuarioPutState.message?.let { message ->
                    Toast.makeText(context, "Erro: $message", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    if(usuarioState is ApiState.Loading || usuarioPutState is ApiState.Loading) {
        LoadingScreen()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EditarUsuarioPreview() {
    EditarUsuario(id = "1", navController = rememberNavController())
}