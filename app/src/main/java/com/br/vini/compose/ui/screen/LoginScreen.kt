package com.br.vini.compose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.br.vini.compose.R
import com.br.vini.compose.api.ApiState
import com.br.vini.compose.viewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    val authViewModel = viewModel<AuthViewModel>()
    val loginState by authViewModel.loginResponseBody

    var email by remember { mutableStateOf("alx.delira@gmail.com") }
    var senha by remember { mutableStateOf("12345678") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ifro),
                contentDescription = "Logo do ifro",
                modifier = Modifier
                    .padding(top = 130.dp, bottom = 130.dp)
                    .size(200.dp)
            )
                when(loginState) {
                    is ApiState.Created -> {}
                    is ApiState.Loading -> {}
                    is ApiState.Success -> {
                        navController.navigate("usuario")
                    }
                    is ApiState.Error -> {
                        loginState.message?.let { message ->
                            Text(
                                message,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = email ,
                    onValueChange = {email = it},
                    label = { Text(
                        "Usuário", style = TextStyle(
                            color = Black
                        ))},
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Gray,
                        unfocusedBorderColor = Black,
                        cursorColor = Black
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = senha,
                    onValueChange = {senha = it},
                    label = {Text ("Senha",
                        style = TextStyle(
                            color = Black))
                            },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            val id = if (passwordVisibility) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on
                            Icon(
                                painter = painterResource(id = id),
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Gray,
                        unfocusedBorderColor = Black,
                        cursorColor = Black
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Password)
                )
            Button(
                onClick = {
                    authViewModel.login(
                        email,
                        senha
                    )
                },
                Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .height(55.dp)
                    ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )

            ) {
                Text(
                    text = "Entrar",
                    color = Color.White)
            }
        }
        if(authViewModel.loading.value) {
            LoadingScreen()
        }
    }

}


