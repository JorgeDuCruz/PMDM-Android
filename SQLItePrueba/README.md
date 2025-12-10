# Funcionamiento del código

## Preaparación de la conexión
```
object FeedReaderContract {
    const val TAG = "SQLite"

    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "subtitle"
    }

    private const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                "${FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"


    private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
```
Primero creamos un `object` con las constantes de nombres de columnas y script de creación de la tabla.

```
class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            Log.d(TAG,"Creando base")
            db.execSQL(SQL_CREATE_ENTRIES)
            Log.d(TAG,"Creada base")
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.d(TAG,"Actualizando base")
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.d(TAG,"desactualizando base")
            onUpgrade(db, oldVersion, newVersion)
        }
        companion object {
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "FeedReader.db"
        }
    }
```
Luego, dentro del `object`, creamos una clase que extienda de `SQLiteOpenHelper`.
Esta clase simplemente sobrescribirá los métodos de `onCreate`, `onUpgrade` y según la guía él `onDowngrade`.

## Acciones en SQLite

```
val dbHelper = FeedReaderDbHelper(application)
        val TAG = "PruebaSQLite"

        Log.d(TAG,"Conectando base")
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(FeedEntry.COLUMN_NAME_TITLE, "My Title")
            put(FeedEntry.COLUMN_NAME_SUBTITLE, "prueba2")
        }
        Log.d(TAG,"Creada fila")
        
        val newRowId = db?.insert(FeedEntry.TABLE_NAME, null, values)
        Log.d(TAG,"insertados datos $newRowId")
```

Para acceder a la base de datos creamos una instancia de la clase Helper pasándole el "contexto" de la aplicación.

Ahora, primero vamos a hacer una referencia a la base de datos en modo escritura gracias al `dbHelper`,
Luego usamos `ContentValues()` para guardar en una variable los datos de la fila a insertar, para evitar fallos los nombres de las columnas
son sacados de las constantes del `object` creado antes, y después de crear la variable con los valores hacemos un insert con `db` que necesita el nombre de la tabla un null y los valores a añadir.
Este método devuelve el id de la fila creada.


```
val dbl = dbHelper.readableDatabase
        Log.d(TAG,"Conectando base lectura")

        val projection = arrayOf(BaseColumns._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE)

        Log.d(TAG,"Filtrando select")

        val selection = "${FeedEntry.COLUMN_NAME_TITLE} = ?"
        val selectionArgs = arrayOf("My Title")

        Log.d(TAG,"Ordenando select")

        val sortOrder = "${FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = dbl.query(
            FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        Log.d(TAG,"valores recibidos")


        val itemIds = mutableListOf<String>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE))
                Log.d(TAG,"Valor = $itemId")

                itemIds.add(itemId)
            }
        }
        Log.d(TAG,"Valores = $itemIds")

        cursor.close()
```

Para leer los datos que hemos guardado necesitamos crear otra referencia a la base de datos pero en modo lectura.

Primero necesitamos hacer una query, similar al insert de antes, pero ahora devuelve un cursor. Este cursor es una referencia a los índices de los resultados,
por lo que necesitamos recorrer el cursor con `moveToNext()`, y con ayuda de un `while` y un `with` podemos recoger los valores de la columna o columnas que le indiquemos yendo fila por fila.

Es importante que cuando terminemos de recoger los datos del cursor le hagamos un `.close()` para liberar sus recursos.

```
        val title = "MyNewTitle"
        val valuesA = ContentValues().apply {
            put(FeedEntry.COLUMN_NAME_TITLE, title)
        }

        val selectionA = "${FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgsA = arrayOf("My Title")
        val count = db.update(
            FeedEntry.TABLE_NAME,
            valuesA,
            selectionA,
            selectionArgsA)
        Log.d(TAG,"Actualizando base $count")
```

Para actualizar datos primero creamos una variable con los datos actualizados,
después usamos, al igual que con el select, una selección de a quien actualizarle los valores y finalmente 
usar `.update()` con los valores a actualizar y los filtros de a quien actualizar.

El método `.update()` devuelve el número de columnas actualizadas.


```
val cursorA = dbl.query(
            FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selectionA,              // The columns for the WHERE clause
            arrayOf(title),          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        Log.d(TAG,"valores recibidos")


        val itemIdsA = mutableListOf<String>()

        with(cursorA) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE))
                Log.d(TAG,"Valor = $itemId")

                itemIdsA.add(itemId)
            }
        }
        Log.d(TAG,"Valores = $itemIdsA")

        cursor.close()
```
Repito los pasos del select para verificar que se actualizaran.

