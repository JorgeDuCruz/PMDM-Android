package dam.pmdm.ejemplotest

import com.google.common.truth.Truth.assertThat // ¡Recomendado! Import de Truth para aserciones legibles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach // <-- JUNIT 5
import org.junit.jupiter.api.BeforeEach // <-- JUNIT 5
import org.junit.jupiter.api.DisplayName // <-- JUNIT 5 (Opcional, para nombres bonitos)
import org.junit.jupiter.api.Test // <-- JUNIT 5

@ExperimentalCoroutinesApi
class MyViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MyViewModel

    // @BeforeEach se ejecuta ANTES de cada test. Es el equivalente de @Before en JUnit 4.
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MyViewModel()
    }

    // @AfterEach se ejecuta DESPUÉS de cada test. Es el equivalente de @After en JUnit 4.
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    // @DisplayName es una anotación de JUnit 5 que permite poner nombres descriptivos a los tests.
    @DisplayName("addRandom() debe devolver un número entre 0 y 9")
    fun addRandom_shouldReturnNumberBetween0And9() = runTest {
        // --- ARRANGE ---
        // El ViewModel ya está creado en setUp()

        // --- ACT ---
        // Llamamos al método que queremos probar
        val numeroAleatorio = viewModel.addRandom()

        // --- ASSERT ---
        // Comprobamos que el número está en el rango esperado usando Truth
        assertThat(numeroAleatorio).isIn(0..9)
        // La aserción con Truth es más legible que la de JUnit y da mejores mensajes de error.
    }
}

