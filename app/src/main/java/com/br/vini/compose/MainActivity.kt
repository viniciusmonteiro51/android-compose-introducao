package com.br.vini.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.br.vini.compose.ui.theme.ComposeIntroducaoTheme
import com.br.vini.compose.ui.screen.InicioScreen
import com.br.vini.compose.ui.screen.LoginScreen
import com.br.vini.compose.ui.screen.MinhaContaScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntroducaoTheme {
            val navController = rememberNavController()
                Surface() {
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ){
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("minha-conta"){
                            MinhaContaScreen(navController)
                        }
                        composable("inicio"){
                            InicioScreen(navController)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    MinhaContaScreen(rememberNavController())
}