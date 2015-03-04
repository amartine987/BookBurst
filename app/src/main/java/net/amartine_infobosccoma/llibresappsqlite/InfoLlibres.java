package net.amartine_infobosccoma.llibresappsqlite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.amartine_infobosccoma.llibresappsqlite.Model.Llibres;

import java.util.Date;



public class InfoLlibres extends ActionBarActivity {
    private Button btn;
    private String titol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_llibres);
        // recuperar les dades
        // Bundle dades = getIntent().getExtras();
        Llibres dades = (Llibres) getIntent().getExtras().getSerializable("Llibres");

        // Obtenir objectes de la Gui
        ImageView img = (ImageView) findViewById(R.id.detailImg);
        TextView nom = (TextView) findViewById(R.id.detailTitol);
        TextView autor = (TextView) findViewById(R.id.detailAutor);
        TextView tipus = (TextView) findViewById(R.id.detailTipus);
        TextView desc = (TextView) findViewById(R.id.detailDesc);

        // Assignar dades a la Gui
        img.setImageResource(dades.getImg());
        nom.setText(dades.getNom());
        autor.setText(dades.getAutor());
        tipus.setText(dades.getTipus());
        desc.setText(dades.getDescripcio());
        titol = dades.getNom();
        btn = (Button) findViewById(R.id.btn_findBook);
        btn.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String query = "http://www.goodreads.com/search?utf8=%E2%9C%93&search%5Bquery%5D=";
                        String fquery = "&search_type=books";
                        String[] str = titol.split(" ");
                        for (int i = 0; i<str.length;i++){
                            if(i==0){
                                query = query + str[i];
                            }else{
                                query = query + "+" + str[i];
                            }
                        }
                        query = query + fquery;
//                --> Va a internet a cercar una web
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(query));
                        startActivity(intent);
                    }
                }).start();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_llibres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(Date data) {
        Toast.makeText(this, "click al boto detectat!" + data, Toast.LENGTH_LONG).show();
    }
}
