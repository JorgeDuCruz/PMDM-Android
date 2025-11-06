package dam.pmdm.ejemplotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {
    // Instanciamos el ViewModel, la lógica de mi aplicación
    private val model: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // cargamos la interfaz grafica, enviandole el ViewModel
            IU(model)
        }
    }
}



