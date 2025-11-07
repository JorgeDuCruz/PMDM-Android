package dam.pmdm.ejemplotest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

/**
 * Pruebas unitarias para MyViewModel.
 * Usamos JUnit 5 y corutinas.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MyViewModelTestUnitario {
    private lateinit var viewModel: MyViewModel

    // @BeforeEach se ejecuta ANTES de cada test en JUnit 5.
    @BeforeEach
    fun setUp() {
        viewModel = MyViewModel()
    }

    @Test
    @DisplayName("El valor inicial de currentName debe ser '(valor inicial)'")
    fun initialValueIsSet() = runTest {
        val vm = MyViewModel()
        // comparamos con el value directo del StateFlow
        assertEquals("(valor inicial)", vm.currentName.value)

        // también podemos recoger el primer valor
        val firstValue = vm.currentName.first()
        assertEquals("(valor inicial)", firstValue)
    }

    @Test
    @DisplayName("addRandom debería devolver un número entre 0 y 9")
    fun addRandom_shouldReturnNumberBetween0And9() {
        // --- ACT ---
        val numeroAleatorio = viewModel.addRandom()

        // --- ASSERT ---
        assertTrue(numeroAleatorio in 0..9, "Entre 0 y 9")
    }

    @Test
    @DisplayName("actualizaNumero debe actualizar currentName (StateFlow) y ser entero")
    fun actualizaNumeroUpdatesCurrentName() = runTest {
        // Act
        viewModel.actualizaNumero()

        // Assert: leer el value actual del StateFlow
        val value = viewModel.currentName.first()
        val intVal = value?.toIntOrNull()
        assertTrue(intVal != null && intVal in 0..9)
    }
}
