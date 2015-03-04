package net.amartine_infobosccoma.llibresappsqlite.SQLite;

import android.app.Activity;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import net.amartine_infobosccoma.llibresappsqlite.Model.Llibres;
import net.amartine_infobosccoma.llibresappsqlite.R;

/**
 * Classe que implementa un adaptador de dades per un ListView
 *
 * @author Marc Nicolau Reixach (marc.nicolau@gmail.com)
 *
 */
public class LlibresAdapter extends BaseAdapter {
    private Activity context;
    private Cursor dades;

    /**
     * Constructor
     * @param context el context de l'aplicació
     * @param dades cursor amb les dades
     */
    public LlibresAdapter(Activity context, Cursor dades) {
        super();
        this.context = context;
        this.dades = dades;
    }

    /**
     * Sobreescriptura del mètode getView per indicar com s'han de mostrar
     * les dades d'una fila del ListView
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        Llibres t = getItem(position);

        if(element == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            element = inflater.inflate(R.layout.listitem_llibre, null);
        }
        TextView txtNom = (TextView)element.findViewById(R.id.txt_titol);
        txtNom.setText(t.getNom());

        ImageView imgvLlibre = (ImageView)element.findViewById(R.id.imgv_llibre);
        imgvLlibre.setImageResource(t.getImg());

        RatingBar rtbar = (RatingBar)element.findViewById(R.id.ratingBar);
        rtbar.setRating(t.getRating());

        return element;
    }

    /**
     * Sobreescriptura del mètode getCount que indica quantes dades gestiona
     * l'adaptador.
     */
    public int getCount() {
        return dades.getCount();
    }
    /**
     * Sobreescriptura del mètode getItem que retorna l'objecte que ocupa la
     * posició indicada amb el paràmetre.
     */
    public Llibres getItem(int pos) {
        Llibres t = new Llibres();
        if(dades.moveToPosition(pos)) {
            t.setId(dades.getInt(0));
            t.setNom(dades.getString(1));
            t.setAutor(dades.getString(2));
            t.setRating(dades.getInt(6));
        }
        return t;
    }
    /**
     * Sobreescriptura del mètode getItemId que retorna l'id de l'objecte
     * que ocupa la posició indicad amb el paràmetre.
     */
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
