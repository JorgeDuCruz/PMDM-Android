# Test con Gradle

Android Studio crea la estructura de carpetas automáticamente. Los tests unitarios deben ir en el directorio `test`, no en `androidTest`.

- **Ruta:** `app/src/test/java/dam/pmdm/ejemplotest/`

`MyViewModelTest.kt` contendrá los tests para el `ViewModel`.

## Librerías adicionales para testing

1.  **JUnit:** Para la estructura del test (`@Test`, `@Before`, `assertEquals`).
2.  **Truth (Opcional pero recomendado):** Una librería de Google que hace las aserciones (assertions) mucho más legibles que las de JUnit.
3. **Coroutines-Test:** Para probar `StateFlow` y corrutinas, necesitas esta librería para controlar el flujo del tiempo.

## Configuración de Gradle

Para la configuración de test instrumentado, necesitas agregar las siguientes dependencias en tu archivo `build.gradle.kts` (o `build.gradle`) del módulo `app`:

```kotlin
defaultConfig {
    // ...tu configuración (minSdk, targetSdk, etc.) aquí...
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
```
Abre tu archivo `build.gradle.kts` (o `build.gradle`) del módulo `app` y asegúrate de que estas dependencias están en la sección `dependencies`:

# Tipos de Test

## Test instrumentado
Los tests instrumentados se ejecutan en un dispositivo Android o emulador. 

Estos tests son útiles para probar la interacción con la interfaz de usuario y componentes específicos de Android.

Los ejecutamos en la carpeta `androidTest`. Con el comando "run", como cuando ejecutamos la app.

## Test unitario
Los tests unitarios se ejecutan en la JVM local de tu computadora.

Estos tests son ideales para probar la lógica de negocio (ViewModel) y componentes que no dependen directamente del framework de Android.

Los ejecutamos en la carpeta `test`.

Con el comando "./gradlew test", antes tenemos que especificar el jre de Java, en la variable de entorno "JAVA_HOME"

## Migración a JUnit 5 (JUnit Jupiter)

Si quieres usar JUnit 5 para tus tests unitarios en `app/src/test`, añade las dependencias de JUnit Jupiter y configura Gradle para usar la plataforma JUnit.

También es recomendable añadir soporte para las librerías para pruebas con corrutinas.

Ejemplo (agrega esto en `app/build.gradle.kts` dentro de `dependencies`):

```kotlin
// JUnit 5 (Jupiter)
testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

// Librerías útiles
testImplementation("androidx.arch.core:core-testing:2.2.0") // InstantTaskExecutorRule para LiveData
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // para pruebas con coroutines

// (Opcional) Asserts más legibles, p.ej. Truth
testImplementation("com.google.truth:truth:1.1.4")
```

Y añade esta configuración para que Gradle ejecute las tareas `Test` usando JUnit Platform y genere reportes HTML/XML:

```kotlin
// dentro del módulo app (build.gradle.kts)
tasks.withType<Test>().configureEach {
    useJUnitPlatform() // activa JUnit 5
    reports {
        junitXml.required.set(true)
        html.required.set(true)
    }
}
```

### Cómo ejecutar los tests y ver el reporte HTML

- Ejecutar todos los tests unitarios del proyecto:

```bash
./gradlew test
```

- Ejecutar los tests unitarios de la variante `debug` del módulo `app` (común en Android):

```bash
./gradlew :app:testDebugUnitTest
```

- Después de ejecutar, el informe HTML estará en el directorio del módulo, por ejemplo:

```
app/build/reports/tests/testDebugUnitTest/index.html
```

### Notas y recomendaciones

- Los tests instrumentados (`androidTest`) siguen ejecutándose con el runner de Android y normalmente no usan JUnit 5.
- Asegúrate de tener `JAVA_HOME` correctamente configurado antes de ejecutar Gradle en la terminal.
  - MacOS: export JAVA_HOME="/Users/nombre_usuario/Applications/Android Studio.app/Contents/jbr/Contents/Home"
- Algunos test utilizan la clase `Log`de Android. Para evitar errores en tests unitarios, puedes usar bibliotecas como `mockk` o `robolectric` para simular el comportamiento de Android.
  - Otra manera es crear una clase de utilidad que maneje los logs y puedas mockearla en los tests (ver ejemplo `Log.java`)
