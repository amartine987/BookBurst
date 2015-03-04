package net.amartine_infobosccoma.llibresappsqlite.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LlibresSQLiteHelper extends SQLiteOpenHelper {

    //Sentència sql per crear la taula llibres
    private String sqlCreate = "CREATE TABLE Llibres (" +
            "id INTEGER PRIMARY KEY," +
            "nom TEXT," +
            "autor TEXT," +
            "tipus TEXT," +
            "descripcio TEXT," +
            "image INTEGER," +
            "rating INTEGER)";

    public LlibresSQLiteHelper(Context context, String nom,
                               SQLiteDatabase.CursorFactory factory, int versio) {
        super(context, nom, factory, versio);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}