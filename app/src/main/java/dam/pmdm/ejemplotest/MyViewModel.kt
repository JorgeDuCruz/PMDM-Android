package dam.pmdm.ejemplotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

// ViewModel que contiene la lógica y los datos de la aplicación
class MyViewModel : ViewModel() {
    private val TAG_LOG = "miDebug"

    // Creamos la variable a observar
    val currentName: MutableStateFlow<String?> = MutableStateFlow("(valor inicial)")

    val currentStateAux: MutableStateFlow<String?> = MutableStateFlow(EstadosAuxiliares.AUX1.txt)

    // funcion para actualizar la variable
    // necesitamos usar la propiedad "value" ya que es un MutableStateFlow
    fun addRandom(): Int {
        val enteroRandom = Random.nextInt(0, 10)
        estadosAuxiliares()
        return enteroRandom
    }

    fun actualizaNumero(){
        val numeroActual = addRandom() // si ponemos algo diferente a un enter el test falla
        currentName.value = numeroActual.toString()
        Log.d("actualiza", "actualizo: $numeroActual")
    }


    /**
     * Corutina que lanza estados auxiliares
     */
    fun estadosAuxiliares() {
        viewModelScope.launch {
            // guardamos el estado auxiliar
            var estadoAux = EstadosAuxiliares.AUX1

            // hacemos un cambio a tres estados auxiliares
            Log.d(TAG_LOG, "estado (corutina): ${estadoAux}")
            currentStateAux.value = estadoAux.txt
            delay(3000)
            estadoAux = EstadosAuxiliares.AUX2
            Log.d(TAG_LOG, "estado (corutina): ${estadoAux}")
            delay(3000)
            currentStateAux.value = estadoAux.txt
            estadoAux = EstadosAuxiliares.AUX3
            Log.d(TAG_LOG, "estado (corutina): ${estadoAux}")
            delay(3000)
            currentStateAux.value = estadoAux.txt
        }
    }

    enum class EstadosAuxiliares(val txt: String) {
        AUX1(txt = "aux1"),
        AUX2(txt = "aux2"),
        AUX3(txt = "aux3"),
    }
}