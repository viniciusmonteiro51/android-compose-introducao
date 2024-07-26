package com.br.vini.compose.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.br.vini.compose.R
import com.br.vini.compose.viewModel.AuthViewModel
import androidx.compose.material3.Text as Text


@Composable
fun MinhaContaScreen(navController: NavHostController) {
    val authViewModel = hiltViewModel<AuthViewModel>()
    val showAlert = remember { mutableStateOf(false) }
    Surface(
    modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Minha conta",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    modifier = Modifier.clip(shape = CircleShape),
                    contentDescription = "Foto de perfil",
                    painter = painterResource(id = R.drawable.macaco)
                )
                Button(
                    onClick = {

                    },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(Icons.Default.Create, contentDescription = "ícone de lupa")
                }

            }
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = "Vinicius Daniel Monteiro",
                color = Color.Black
            )
            Text(
                text = "vinicius.m@estudante.ifro.edu.br",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Gray
                )
            )
            Button(
                onClick = {
                    showAlert.value = true
                },
                modifier = Modifier.padding(top = 24.dp)
            ) { Text(text = "Sair") }
        }

        if (showAlert.value) {
            AlertDialog(onDismissRequest = {
                showAlert.value = false
            },
                title = {
                    Text(text = "Tem certeza que deseja sair?")
                },
                text = {
                    Text("Não será possível acessar informações e receber notificações pessoais.")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showAlert.value = false
                        }
                    ) {
                        Text(text = "Sair")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showAlert.value = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}





