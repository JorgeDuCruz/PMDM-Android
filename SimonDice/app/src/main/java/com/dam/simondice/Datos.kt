package com.dam.simondice
import androidx.compose.ui.graphics.Color

/**
 * Clase para almacenar los datos del juego
 * @property ronda Número de ronda actual
 * @property secuencia Secuencia de colores
 * @property secuenciaUsuario Secuencia de colores introducida por el usuario
 * @property record Record de puntuación
 * @property estado Estado del juego
 */
object Datos {
    var numero = 0
    var ronda = 0
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estado = Estado.INICIO
}

/**
 * Enum con los estados del juego
 *
 */

enum class Estado(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true,boton_activo = false),
    GENERANDO(start_activo = false,boton_activo = false),
    ADIVINANDO(start_activo = false,boton_activo = true),
}

/**
 * Colores utilizados
 */

enum class Colores(val color: Color, val txt: String) {
    CLASE_ROJO(color = Color.Red, txt = "Rojo"),
    CLASE_VERDE(color = Color.Green, txt = "Verde"),
    CLASE_AZUL(color = Color.Blue, txt = "Azul"),
    CLASE_AMARILLO(color = Color.Yellow, txt = "Amarillo")
}