package com.br.vini.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.br.vini.compose.ui.theme.ComposeIntroducaoTheme
import com.br.vini.compose.ui.theme.InicioScreen
import com.br.vini.compose.ui.theme.LoginScreen
import com.br.vini.compose.ui.theme.MinhaContaScreen

class MainActivity : ComponentActivity() {
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

@Composable
fun App() {
    //InicioScreen()
    //LoginScreen()
    //MinhaContaScreen()
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}