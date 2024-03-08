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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.br.vini.compose.R
import com.br.vini.compose.viewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    val authViewModel = hiltViewModel<AuthViewModel>()
    var user by remember {mutableStateOf("")}
    var senha by remember {mutableStateOf("")}
    var error by remember { mutableStateOf("") }

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
            if(error.isNotEmpty()) {
                Text(text = error)
            }
                OutlinedTextField(
                    value = user ,
                    onValueChange = {user = it},
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
                    label = {Text (
                        "Senha",
                        style = TextStyle(
                            color = Black))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Gray,
                        unfocusedBorderColor = Black,
                        cursorColor = Black
                    ),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Password)
                )
            Button(
                onClick = {
                    error = ""
                   authViewModel.login(
                              user,
                              senha,
                              onSucess = {
                                  navController.navigate("minha-conta")
                              },
                              onError = { message ->
                                  error = message
                              }
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


