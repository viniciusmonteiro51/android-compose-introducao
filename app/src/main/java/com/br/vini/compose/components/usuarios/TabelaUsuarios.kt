/*
package com.br.vini.compose.components.usuarios

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.vini.compose.api.model.Usuario
import com.br.vini.compose.R
import com.br.vini.compose.api.response.UsuariosResponseBody

@Composable
fun TabelaUsuarios(usuario: UsuariosResponseBody) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { }
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            },
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.macaco),
            contentDescription = "Foto",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),


        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = usuario.nome)
            Text(text = usuario.email)
        }

    }
    
}


@Preview(showSystemUi = true)
@Composable
private fun TabelaUsuariosPreview() {
    TabelaUsuarios(usuario = Usuario())
}
*/