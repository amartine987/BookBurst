package net.amartine_infobosccoma.llibresappsqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import net.amartine_infobosccoma.llibresappsqlite.Model.Llibres;
import net.amartine_infobosccoma.llibresappsqlite.SQLite.LlibresConversor;
import net.amartine_infobosccoma.llibresappsqlite.SQLite.LlibresSQLiteHelper;

/*
 * Activitat que permet introduir un nou Llibre a la BD
 */
public class NouLlibreActivity extends Activity {
    private boolean editar;
   // private Llibres llibEditar = new Llibres(1,"titol","yolooo","patata","poma",R.drawable.book,4);
    private Llibres llibEditar;
    private EditText txtNom;
    private EditText txtAutor;
    private EditText txtTipus;
    private EditText txtDesc;
   // private EditText txtImage;
    private EditText txtRating;
    private Button btnOk;
    private Button btnCancel;

    public NouLlibreActivity(){

    }

    public NouLlibreActivity(Llibres llibEditar, boolean editar){
        this.llibEditar = llibEditar;
        this.editar = editar;
    }
    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou_llibre);
        Intent i = getIntent();
        llibEditar = (Llibres) i.getSerializableExtra("informacio");
        editar = i.getBooleanExtra("enviar",false);

        txtNom = (EditText) findViewById(R.id.add_nom);
        txtAutor = (EditText) findViewById(R.id.add_autor);
        txtTipus = (EditText) findViewById(R.id.add_tipus);
        txtDesc = (EditText) findViewById(R.id.add_desc);
        txtRating = (EditText) findViewById(R.id.add_rating);
        //txtImage = (EditText) findViewById(R.id.add_img);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        if(editar){
            carregar();
        }
        btnOk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(editar){
                    actualitzar();
                }else{
                    desar();
                }
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    private void actualitzar(){
        try {
            LlibresSQLiteHelper llibHelp = new LlibresSQLiteHelper(this, "Llibres.db", null, 2);
            LlibresConversor llibConv = new LlibresConversor(llibHelp);
            // obtenir l'objecte BD
            SQLiteDatabase db = llibHelp.getWritableDatabase();
            llibEditar.setNom(txtNom.getText().toString());
            llibEditar.setAutor(txtAutor.getText().toString());
            llibEditar.setTipus(txtTipus.getText().toString());
            llibEditar.setDescripcio(txtDesc.getText().toString());
            llibEditar.setRating(Integer.parseInt(txtRating.getText().toString()));
            llibConv.updateLlibre(llibEditar);
        } catch (Exception e) {
            setResult(RESULT_CANCELED);
        } finally {
            finish();
        }
        editar = false;
    }
    /**
     * Mètode que desa les dades introduïdes per l'usuari
     */
    private void desar() {
        try {
            LlibresSQLiteHelper llibHelp = new LlibresSQLiteHelper(this, "Llibres.db", null, 2);
            LlibresConversor llibConv = new LlibresConversor(llibHelp);
            // obtenir l'objecte BD
            SQLiteDatabase db = llibHelp.getWritableDatabase();
            // Si s'ha obert correctament la BD
            if (db != null) {
                int id;
                if(!editar){
                    MainActivity ma = new MainActivity();
                    id = ma.getNumRegistres() + 1;
                    ma.setNumRegistres(id);
                }else{
                    id = llibEditar.getId();
                }
                // esborrar tots els registres de la taula
                llibConv.save(new Llibres(id, txtNom.getText().toString(),
                        txtAutor.getText().toString(),
                        txtTipus.getText().toString(),
                        txtDesc.getText().toString(),
                        //Integer.parseInt(txtImage.getText().toString()),
                        R.drawable.book,
                        Integer.parseInt(txtRating.getText().toString())));
                setResult(RESULT_OK);
            }
        } catch (Exception e) {
            setResult(RESULT_CANCELED);
        } finally {
            finish();
        }
    }
    public void setLlibEditar(Llibres llib){
        this.llibEditar = llib;
    }
    public void setEditar(boolean editar){
        this.editar = editar;
    }
    public void carregar(){
        txtNom.setText(llibEditar.getNom().toString());
        txtAutor.setText(llibEditar.getAutor().toString());
        txtTipus.setText(llibEditar.getTipus().toString());
        txtDesc.setText(llibEditar.getDescripcio().toString());
        txtRating.setText(llibEditar.getRating() + "");
    }
}
