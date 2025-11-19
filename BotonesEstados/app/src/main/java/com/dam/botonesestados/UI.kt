package com.dam.botonesestados

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UI(myViewModel: MyViewModel){
    val estadoActual by myViewModel._estadoActual.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column {
            Extra(estadoActual, myViewModel = myViewModel)
            Texto(estadoActual = estadoActual.name)
            BotonEstados(myViewModel)
            BotonCambiable(estadoActual)
        }
    }
}

@Composable
fun BotonCambiable(estadoActual: Estados) {
    var funcion = estadoActual.onClick
    Button (onClick = funcion) { Text(text = "Cambiar Estado")}
}

@Composable
fun BotonEstados(myViewModel: MyViewModel) {
    Button (onClick = {myViewModel.CambiarEstado()}) { Text(text = "Cambiar Estado")}
}

@Composable
fun Texto(estadoActual: String) {
    Text(
        text = "Estado actual $estadoActual"
    )
}

@Composable
fun Extra(estadoActual: Estados,myViewModel: MyViewModel){
    if (estadoActual == Estados.ESTADO1){
        val texto by myViewModel._mensaje.collectAsState()
        Text(text = "Texto $texto")
    }
    else if (estadoActual == Estados.ESTADO2){
        val cuentaAtras by  myViewModel._cuentaAtras.collectAsState()
        Text(text = "$cuentaAtras", textAlign = TextAlign.Center)

    }

}

@Preview(showBackground = true)
@Composable
fun UIPreview(){
    UI(MyViewModel())
}