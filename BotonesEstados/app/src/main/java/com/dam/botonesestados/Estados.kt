package com.dam.botonesestados

import android.util.Log

enum class Estados (val onClick:()->Unit){
    ESTADO1({
        Log.d("Preuea","Estado 1")
        MyViewModel.mostrarMensaje()
    }),
    ESTADO2({ Log.d("Preuea","Estado 2")
        MyViewModel.CuentaAtras()
    }),
    ESTADO3({ Log.d("Preuea","Estado 3")
        MyViewModel.hacerSonido()
    })
}