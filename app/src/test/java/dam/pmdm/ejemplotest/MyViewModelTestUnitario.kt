package dam.pmdm.ejemplotest

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName


class MyViewModelTestUnitario {
    private lateinit var viewModel: MyViewModel

    // @BeforeEach se ejecuta ANTES de cada test. Es el equivalente de @Before en JUnit 4.
    @Before
    fun setUp() {
        viewModel = MyViewModel()
    }


    @Test
    @DisplayName("addRandom debería devolver un número entre 0 y 9")
    fun addRandom_shouldReturnNumberBetween0And9() {
        // --- ARRANGE ---
        // El ViewModel ya está creado en setUp()

        // --- ACT ---
        // Llamamos al método que queremos probar
        val numeroAleatorio = viewModel.addRandom()

        // --- ASSERT ---
        // Comprobamos que el número está en el rango esperado usando Truth
        assertTrue("Entre 0 y 9", numeroAleatorio in 0..9)

        println("numero aleatorio: $numeroAleatorio")
    }
}

