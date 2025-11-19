package com.dam.botonesestados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object MyViewModel: ViewModel() {

    val _estadoActual = MutableStateFlow<Estados>(Estados.ESTADO1)
    val _cuentaAtras = MutableStateFlow<Int>(3)
    val _mensaje = MutableStateFlow<String>("")

    fun CambiarEstado(){
        if (_estadoActual.value == Estados.ESTADO1){
            _estadoActual.value = Estados.ESTADO2
        }else if (_estadoActual.value == Estados.ESTADO2){
            _estadoActual.value = Estados.ESTADO3
        }else{
            _estadoActual.value = Estados.ESTADO1
        }
    }

    fun CuentaAtras(){
        _cuentaAtras.value = 4
        viewModelScope.launch {
            while (_cuentaAtras.value > 0) {
                _cuentaAtras.value--
                delay(1000)
            }
        }
    }

    fun mostrarMensaje(){
        if (_mensaje.value.isEmpty()){
            _mensaje.value = "Texto a mostrar"
        }else{
            _mensaje.value = ""
        }
    }



}