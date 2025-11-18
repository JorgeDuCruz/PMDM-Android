package com.dam.simuladordescargas

import android.util.Range
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyViewModel(): ViewModel() {
    val _estadoFlow = MutableStateFlow<Estados>(Estados.INICIO)
    val _progresoFlow = MutableStateFlow<Int>(0)
    val _resultadoFlow = MutableStateFlow<Boolean>(true)
    var rutina: Job? = null

    fun simularDescarga(){
        if (rutina != null && rutina!!.isActive) rutina!!.cancel()
        rutina = viewModelScope.launch {
            _progresoFlow.value = 0
            delay(1000)
            var numRandom = generarRandom()
            numRandom *=500
            _estadoFlow.value = Estados.CARGANDO
            while (true) {
                if (_progresoFlow.value >= 100) break
                _progresoFlow.value = _progresoFlow.value.plus(10)

                delay(numRandom.toLong())
            }
            _estadoFlow.value = Estados.FINALIZADO
            _resultadoFlow.value = true
        }

    }

    fun generarRandom(): Int {
      var num =  (1..10).random()
      return num
    }


}