package net.amartine_infobosccoma.llibresappsqlite.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.amartine_infobosccoma.llibresappsqlite.Model.Llibres;

/**
 * Classe conversora d'objectes Titular a BD
 *
 * @author Marc Nicolau Reixach
 *
 */
public class LlibresConversor {

    private LlibresSQLiteHelper helper;

    /**
     * Consructor per defecte
     */
    public LlibresConversor() {

    }

    /**
     * Constructor amb paràmetres
     * @param helper l'ajudant de la BD de Titulars
     */
    public LlibresConversor(LlibresSQLiteHelper helper) {
        this.helper = helper;
    }

    public int updateLlibre(Llibres llib) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues dades = new ContentValues();
        dades.put("id",llib.getId());
        dades.put("nom", llib.getNom());
        dades.put("autor", llib.getAutor());
        dades.put("tipus", llib.getTipus());
        dades.put("descripcio", llib.getDescripcio());
        dades.put("image", llib.getImg());
        dades.put("rating", llib.getRating());

        // updating row
        return db.update("Llibres", dades, "id= ? ",
                new String[] { String.valueOf(llib.getId()) });
    }
    /**
     * Desa un nou titular a la taula
     * @param llibre l'objecte a desar
     * @return l'id del nou titular desat
     */
    public long save(Llibres llibre) {
        long index = -1;
        // s'agafa l'objecte base de dades en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();
        // es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir
        ContentValues dades = new ContentValues();

        dades.put("nom", llibre.getNom());
        dades.put("autor", llibre.getAutor());
        dades.put("tipus", llibre.getTipus());
        dades.put("descripcio", llibre.getDescripcio());
        dades.put("image", llibre.getImg());
        dades.put("rating", llibre.getRating());
        try {
            index = db.insertOrThrow("Llibres", null, dades);
            // volem veure en el log el que passa
            Log.i("Llibres", dades.toString() + " afegit amb codi " + index);
        }
        catch(Exception e) {
            // volem reflectir en ellog que hi ha hagut un error
            Log.e("Llibres", e.getMessage());
        }
        return index;
    }

    /**
     * Retorna un cursor amb totes les dades de la taula
     * @return
     */
    public Cursor getAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(true, "Llibres",
                new String[]{"id","nom","autor","tipus","descripcio","image","rating"},
                null, null, null, null, null, null);
    }

    public Llibres getItemById(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(true, "Llibres", new String[]{"id","nom","autor","tipus","descripcio","image","rating"},
                "id = ?",new String[]{(String.valueOf(id) )}, null, null, null, null);

        Llibres llibre = null;

        if(c.moveToNext()) {
            llibre = new Llibres(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5),c.getInt(6));
        }
        return llibre;


    }
    /**
     * Esborra el titular passat per paràmetre
     * @param t el titular a esborrar
     * @return la quantitat de titulars eliminats
     */
    public boolean remove(Llibres t) {
        // obtenir l'objecte BD en mode esriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Llibres", "id=" + t.getId(),null ) > 0;
    }
    /**
     * Esborra tots els titulars de la taula
     * @return
     */
    public boolean removeAll() {
        // obtenir l'objecte BD en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Llibres", null, null ) > 0;
    }
}
