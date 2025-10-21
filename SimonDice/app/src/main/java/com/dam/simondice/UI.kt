package com.dam.simondice


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IU() {
    Menu()
}

@Composable
fun Menu(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Puntuacion()
            Botonera()
            Inicio()
        }
    }
}


@Composable
fun Puntuacion(){
    Text(
        text = "Puntuación: ",
        modifier = Modifier.padding(top = 100.dp)
    )
}
/**
 * Botones de colores
 */
@Composable
fun Botonera() {
    Row (modifier = Modifier.padding(16.dp)){
        // Cuatro botones centrados en la pantalla
        Column {
            Log.d("Juego", "Botonera: ${Colores.CLASE_AMARILLO.ordinal}")
            Boton(Colores.CLASE_AMARILLO)
            Log.d("Juego", "Botonera: ${Colores.CLASE_ROJO.ordinal}")
            Boton(Colores.CLASE_ROJO)
        }
        Column {
            Log.d("Juego", "Botonera: ${Colores.CLASE_AZUL.ordinal}")
            Boton(Colores.CLASE_AZUL)
            Log.d("Juego", "Botonera: ${Colores.CLASE_VERDE.ordinal}")
            Boton(Colores.CLASE_VERDE)
        }
    }
}
@Composable
fun Boton(enum_color: Colores) {
    Button(
        // utilizamos el color del enum
        colors =  ButtonDefaults.buttonColors(enum_color.color),
        onClick = { Log.d("Juego","Click!"+ enum_color.txt) },
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .size((150).dp)
            .padding(15.dp)
    ) {
        // utilizamos el texto del enum
        Text(text = enum_color.txt,
            fontSize = 15.sp,
            color = Color.Black)
    }
}

@Composable
fun Inicio(){
    Button(
        onClick = {Log.d("Juego","Empieza el juego")}) {
        Text(
            text = "Start"
        )
    }
}
/**
 * Preview de la interfaz de usuario
 */

@Preview(showBackground = true)
@Composable
fun IUPreview() {
    IU()
}