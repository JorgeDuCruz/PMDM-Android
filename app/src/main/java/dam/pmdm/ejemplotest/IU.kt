package dam.pmdm.ejemplotest

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun IU(model: MyViewModel){

    // variante usando 'by'
    // 'by' es usado para obtener el valor actual de la propiedad.
    // la ventaja es que no es necesario llamar a .value
    // val newName by model.currentName.collectAsState("")

    // Observamos el contenido ('value') de la variable 'currentName' del ViewModel
    val newName = model.currentName.collectAsState().value

    // centramos contenido
    Column(
        modifier = Modifier.fillMaxSize(), // Hace que la columna ocupe todo el ancho y alto.
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente.
    ) {
        // llamamos a las funciones que tienen los objetos de la IU
        ActualizaTexto(newName)
        Boton(model)
    }
}

@Composable
fun ActualizaTexto(newName: String?) {
    Text(text = "Hola $newName!")
    Log.d("actualiza", "actualizo: $newName")

}

@Composable
fun Boton(model: MyViewModel) {
    Button(onClick = { model.actualizaNumero() }) {
        Text(text = "Añade un numero")
    }
}