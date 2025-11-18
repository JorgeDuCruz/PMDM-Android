package com.dam.simuladordescargas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UI (myViewModel: MyViewModel){
    val progreso by myViewModel._progresoFlow.collectAsState()
    val estado by myViewModel._estadoFlow.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Estado(estado)
            Descarga(progreso)
            Boton(myViewModel = myViewModel)
        }
    }
}

@Composable
fun Estado(estado: Estados) {
    Text(
        text = estado.name
    )
}

@Composable
fun Boton(myViewModel: MyViewModel) {
    Button(
        onClick = {
        myViewModel.simularDescarga()},
        ){
        Text(
            text = "Load"
        )
    }
}

@Composable
fun Descarga(progreso: Int){
    var progresoF: Float = progreso*0.01f
    LinearProgressIndicator(
        progress = { progresoF },
        gapSize = ProgressIndicatorDefaults.CircularStrokeWidth
    )
    Text(
        text = "$progreso%"
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewUI(){
    UI(MyViewModel())
}