package dam.pmdm.ejemplotest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyViewModelStateFlowTest {

    @Test
    fun initialValueIsSet() {
        val vm = MyViewModel()
        assertEquals("(valor inicial)", vm.currentName.value)
    }

    @Test
    fun actualizaNumeroUpdatesCurrentName() {
        val vm = MyViewModel()
        // Act
        vm.actualizaNumero()

        // Assert: el value se actualiza inmediatamente
        val value = vm.currentName.value
        val intVal = value?.toIntOrNull()
        assertNotNull(intVal, "currentName debe ser convertible a Int")
        assertTrue(intVal!! in 0..9, "currentName debe estar entre 0 y 9")
    }

    @Test
    fun addRandomReturnsBetween0And9() {
        val vm = MyViewModel()
        val n = vm.addRandom()
        assertTrue(n in 0..9)
    }
}
