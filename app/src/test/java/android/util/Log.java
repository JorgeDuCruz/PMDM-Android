// Mock de la clase android.util.Log para pruebas unitarias
// Necesitamos imitar el paquete que la contiene
package android.util;

/**
 * Version mock(imitacion) android.util.Log para unit testing.
 * Necesitams esta clase para que el test se pueda realizar
 * Los metodos no hacen nada y retornan valores por defecto
 */
public final class Log {
    public static int d(String tag, String msg) { return 0; }
    public static int i(String tag, String msg) { return 0; }
    public static int w(String tag, String msg) { return 0; }
    public static int e(String tag, String msg) { return 0; }
    public static int v(String tag, String msg) { return 0; }
    public static String getStackTraceString(Throwable tr) { return ""; }
}