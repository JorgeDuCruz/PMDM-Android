package com.dam.simuladordescargas

import android.util.Range
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyViewModel(): ViewModel() {
    val _estadoFlow = MutableStateFlow<Estados>(Estados.INICIO)
    val _progresoFlow = MutableStateFlow<Int>(0)
    val _resultadoFlow = MutableStateFlow<Boolean>(true)

    fun simularDescarga(){
        viewModelScope.launch {
            _progresoFlow.value = 0
            var numRandom = generarRandom()
            numRandom *=500
            _estadoFlow.value = Estados.CARGANDO
            while (_progresoFlow.value < 100) {
                delay(numRandom.toLong())
                _progresoFlow.value = _progresoFlow.value.plus(10)
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