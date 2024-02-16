package com.br.vini.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.br.vini.compose.ui.theme.ComposeIntroducaoTheme
import com.br.vini.compose.ui.theme.InicioScreen
import com.br.vini.compose.ui.theme.LoginScreen
import com.br.vini.compose.ui.theme.MinhaContaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntroducaoTheme {

                Surface() {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    //InicioScreen()
    //LoginScreen()
    MinhaContaScreen()
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}