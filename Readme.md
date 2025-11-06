# Test con Gradle

Android Studio crea la estructura de carpetas automáticamente. Los tests unitarios deben ir en el directorio `test`, no en `androidTest`.

- **Ruta:** `app/src/test/java/dam/pmdm/ejemplotest/`

`MyViewModelTest.kt` contendrá los tests para el `ViewModel`.

## Librerías adicionales para testing

Para probar `LiveData` o `Flow` y componentes de `ViewModel`, a veces se necesitan librerías adicionales. Las más importantes son:

1.  **JUnit:** Para la estructura del test (`@Test`, `@Before`, `assertEquals`). Ya debería estar incluida.
2.  **Truth (Opcional pero recomendado):** Una librería de Google que hace las aserciones (assertions) mucho más legibles que las de JUnit.
3.  **InstantTaskExecutorRule:** Para probar `LiveData`, esta regla es crucial. Le dice a los componentes de Arquitectura de Android que ejecuten cualquier operación en segundo plano de forma síncrona en el mismo hilo del test.
4.  **Coroutines-Test:** Para probar `StateFlow` y corrutinas, necesitas esta librería para controlar el flujo del tiempo.

## Dependencias de Gradle

Abre tu archivo `build.gradle.kts` (o `build.gradle`) del módulo `app` y asegúrate de que estas dependencias están en la sección `dependencies`:


