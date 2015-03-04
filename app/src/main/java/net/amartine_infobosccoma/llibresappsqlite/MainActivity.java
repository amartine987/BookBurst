package net.amartine_infobosccoma.llibresappsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import net.amartine_infobosccoma.llibresappsqlite.Model.Llibres;
import net.amartine_infobosccoma.llibresappsqlite.SQLite.LlibresAdapter;
import net.amartine_infobosccoma.llibresappsqlite.SQLite.LlibresConversor;
import net.amartine_infobosccoma.llibresappsqlite.SQLite.LlibresSQLiteHelper;

/**
 * Activitat que mostra un ListView de titulars a partir de les
 * dades d'una base de dades SQLite
 *
 * @author Marc Nicolau Reixach (marc.nicolau@gmail.com)
 *
 */
public class MainActivity extends ActionBarActivity {
    final static int ADD_LLIBRE = 1;

    private Cursor llibres;
    private LlibresAdapter adapter;
    private LlibresSQLiteHelper llibHelper;
    private LlibresConversor llibConv;
    private ListView llista;
    private Llibres llibEnviar;
    private boolean enviar;
    private int numRegistres = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recuperar els controls
        llista = (ListView) findViewById(R.id.lv_llibres);

        // vincular el menú contextual a la llista
        registerForContextMenu(llista);

        // crear l'objecte que crea la connexió amb la BD
        llibHelper = new LlibresSQLiteHelper(this, "Llibres.db", null, 2);
        // obtenir l'objecte BD
        SQLiteDatabase db = llibHelper.getWritableDatabase();
        llibConv = new LlibresConversor(llibHelper);
        // region inserts propis
        //llibConv.removeAll();
//        int id =0;
//        String nom = "The name of the wind";
//        String autor = "Patrick Rotfus";
//        String tipus = "Fantastic";
//        String desc = "La obra es desenvolupa " +
//                "en un món fantàstic (ambientat en la edat mitjana) i narra la història de com Kvothe" +
//                " (pronunciat \"cuouz\"), mag, assassí, enamorat, músic, estudiant i aventurer, es va" +
//                " convertir en un personatge llegendari. Usant el nom de Kote per ocultar la seva veritable" +
//                " identitat, regenta una apartada posada anomenada Pedra Fita acompanyat del seu deixeble Bast," +
//                " fins que un dia Devan Lochees, un autor interessat a escriure les biografies de les figures més" +
//                " importants del seu temps i conegut com a \"Cronista\", el troba i intenta convèncer-lo que li" +
//                " reveli la seva veritable història. Kvothe finalment hi accedeix amb la condició de fer-ho en tres dies.\n" +
//                "\n" +
//                "El Nom del Vent, el primer llibre de la trilogia Crònica del assassí de reis," +
//                "constitueix el primer dels tres dies en què Kvothe explica la seva història. Aquesta comença en els durs anys" +
//                " de la seva infància com a membre de una família de artistes itinerants (els RUH) formada per músics," +
//                " actors, acròbates i joglars. En aquest ambient, creix convertint-se en un nen prodigi alegre i" +
//                " diplomàtic. Un dia coneix a Abenthy, mag i arcanista. Quan el veu cridant al vent el convida a " +
//                "que suneixi a la seva troupe (grup de artistes itinerants). Aquest descobreix en Kvothe un talent " +
//                "natural i decideix convertir-se en el seu primer mestre. Els seus pares són morts pels Xandrian, " +
//                "uns dimonis que maten a aquells que difonen la seva història, així que Kvothe es veurà obligat a " +
//                "passar tres anys pidolant a la gran metròpolis de Trebon on haurà de suportar dures condicions per " +
//                "poder sobreviure. La història evoluciona fins a un sobtat succés que obliga a canviar la seva vida. " +
//                "Després de això pateix una sèrie de esdeveniments i reptes que el porten a viatjar a la Universitat, on," +
//                " músic i estudiant, estudia per poder arribar a ser un gran arcanista.";
//        int image = R.drawable.book;
//        float rating = 5;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                    "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//        id =1;
//        nom = "Fall of Giants";
//        autor = "Ken Follet";
//        tipus = "Novel·la Històrica";
//        desc = "La acció transcorre entre el 22 de juny de 1911, dia en què va ser coronat Jordi V a la Abadia de Westminster" +
//                        " i finals de 1924, diversos anys després de finalitzar la Primera Guerra Mundial. Es barregen personatges " +
//                        "històrics reals com el propi Rei Jordi V, Sir Edward Grey secretari de afers exteriors i Winston Churchill, " +
//                        "amb altres de ficció com Billy Williams, que treballa des dels 13 anys a les mines de carbó de Gal·les i el " +
//                        "seu pare David Williams destacat membre del sindicat miner.\n" +
//                        "\n" +
//                        "La vida dels diferents personatges es veu trasbalsada pels successos que van donar origen a la guerra" +
//                        " i les seves tràgiques conseqüències. Les famílies que formen la part principal de la trama, es van veure " +
//                        "implicades en els esdeveniments bèl·lics de una o altra manera. A través de la narració es pot assistir en " +
//                        "directe a intrigues diplomàtiques, assalts a les trinxeres enemigues, converses mundanes i passions amoroses " +
//                        "de persones que van viure aquelles circumstàncies, el que aproxima el lector a uns fets que van ocórrer fa " +
//                        "gairebé 100 anys i van transformar per sempre el ordre europeu i mundial.";
//        image = R.drawable.book;
//        rating = 5;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//
//        id =2;
//        nom = "Geronimo Stilton";
//        autor = "Elisabetta Dami";
//        tipus = "Infantil";
//        desc = "Geronimo Stilton és un ratolí que viu a una misteriosa" +
//                        " Illa dels Ratolins on tothom pertany a aquesta espècie animal, encara que actuen com éssers humans. " +
//                        "El humor i la estètica visual, que dóna molta importància a les il·lustracions i als jocs gràfics dintre del text," +
//                        " són algunes de les claus del seu èxit. A part dels llibres més petits, també " +
//                        "hi ha els viatges al Regne de la Fantasia, llibres en que Geronimo viu experiències emocionants" +
//                        " en un món fantàstic.";
//        image = R.drawable.book;
//        rating = 3;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//               id =3;
//        nom = "The Hobbit";
//        autor = "J.R.R Tolkien";
//        tipus = "Fantàstic";
//        desc = "Situada al any 2941 de la Tercera Edat del Sol, la història del hòbbit Bilbo arrenca" +
//                " amb la recerca de un tresor. Fins ara la vida del protagonista ha estat molt tranquil·la" +
//                " i rutinària, però tot canviarà quan emprengui camí cap a terres estranyes. Amb la companyia" +
//                " del mag Gandalf i altres personatges que trobaran al llarg del recorregut, les aventures de en Bilbo" +
//                " ens transporten al món imaginari que creà el autor de El senyor dels anells.";
//        image = R.drawable.book;
//        rating = 4;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//        id =4;
//        nom = "Panserhjerte";
//        autor = "Jo Nesbo";
//        tipus = "Novel·la policíaca";
//        desc = "Durant el cru hivern d’Oslo, dues dones apareixen ofegades en la seva pròpia sang. " +
//                "Els investigadors de la policia no troben cap pista. Sembla que torna a tractar-se d’un assassí en sèrie," +
//                " però l’única persona capaç de resoldre un cas d’aquestes característiques és Harry Hole," +
//                " que ha desaparegut a Hong Kong i ofega les penes entre fumadors d’opi i alcohol.\n" +
//                "\n" + "Kaja Solness, una comissària jove, aconseguirà trobar l’amagatall del Harry" +
//                " i el convencerà perquè torni a Oslo, on ha aparegut un tercer cadàver." +
//                " Hi havia cap vincle entre les tres dones mortes? Què hi havien anat a fer, en aquell refugi de muntanya?";
//        image = R.drawable.book;
//        rating = 4;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//        id =5;
//        nom = "Snømannen";
//        autor = "Jo Nesbo";
//        tipus = "Novel·la policíaca";
//        desc = "És novembre i a Oslo acaba de caure la primera nevada de l’any. Una nit, la Birte Becker " +
//                "arriba a casa després de treballar i contempla el ninot de neu que el seu marit i el seu fill " +
//                "Jonas han fet al jardí. Però ells no han fet pas aquest ninot d’ulls negres, que mira la casa fixament. " +
//                "L’endemà, quan en Jonas es lleva la seva mare ha desaparegut. La seva bufanda rosa ara la porta el ninot... " +
//                "El detectiu Harry Hole sospita que hi ha una relació entre la desaparició de la Birte i un anònim" +
//                "que acaba de rebre. Un assassí que ha mort onze dones, sempre durant la primera nevada de l’any, dicta" +
//                " les regles d’un joc macabre, que portaran Harry Hole al límit de la bogeria. \n" +
//                "Amb personatges sòlidament construïts i un suspens que narcotitzarà el lector, " +
//                "Nesbo presenta el cas que toca més de prop el seu detectiu.";
//        image = R.drawable.book;
//        rating = 3;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//        id =6;
//        nom = "La espada de fuego";
//        autor = "Javier Negrete";
//        tipus = "Fantàstic";
//        desc = "Zemal la espasa de foc, és el màxim símbol de poder i la major aspiració per tot guerrer." +
//                " Només els Tahedorans, els grans mestres de la espasa, poden competir per ella. " +
//                "Dresprés de la mort de Hairon, el últim Zemalnit, set aspirants es disputaran la espasa; " +
//                "però hi ha en joc una cosa major que la ambició de poder, doncs forces extranyes estan disposades" +
//                " a trancar la concordia entre homes i deus, i se han unit per tal de aixecar a Tubilok, " +
//                "el deu rebel que dorm fundit en una roca en els abismes del Prates, el resurgir de aquest déu transformarà" +
//                " els seus somnis en els malsons dels humans";
//        image = R.drawable.book;
//        rating = 3;
//        db.execSQL("INSERT INTO Llibres (id, nom, autor, tipus, descripcio,image,rating) " +
//                "VALUES (" + id + ", '" + nom + "', '" + autor + "', '" + tipus + "', '" + desc + "', " + image + ", " + rating + ")");
//Endregion

        // Si s'ha obert correctament la BD
        if (db != null) {
            // actualitzar la llista
            refreshData();
            // Tancar la base de dades
            db.close();
        }
    }

    /*
     * Torna a executar la consulta i a enllaçar les dades
     */
    void refreshData() {
        llibres = llibConv.getAll();
        adapter = new LlibresAdapter(this, llibres);
        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), InfoLlibres.class);
                //Llibres dades = adapter.getItem(position);
                Llibres dades = (Llibres) llibConv.getItemById(position);
                System.out.println("Llibres " + dades.toString());
                Bundle b = new Bundle();
                b.putSerializable("Llibres",dades );
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        if(llibres.getCount() == 0) {
            llista.setVisibility(llista.INVISIBLE);
        }
        else {
            llista.setVisibility(llista.VISIBLE);
        }
    }

    /**
     * Crea el menú d'opcions de l'aplicació
     */
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Respon a l'event d'haver escollit una opció del menú de l'app
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnuAdd:
                Intent i = new Intent(this, NouLlibreActivity.class);
                startActivityForResult(i, ADD_LLIBRE);
                return true;
            case R.id.mnuSortir :
                this.finish();
                return true;
            case R.id.action_info :
                Toast.makeText(this,"Informat de llibres, opinions i reviews\r\n Troba llibres que encara no has llegit i t'han" +
                        "estat esperant aquí! I comenta tots aquells altres que ja has llegit!" +
                        "\r\n Autor: Aitor",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuRefresh :
                refreshData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // si s'ha tancat l'activitat ADD_TITULAR i ha anat bé
        if( requestCode == ADD_LLIBRE && resultCode == RESULT_OK) {
            refreshData();
        }
    }

    /**
     * Crea el menú contextual
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    /**
     * Respon a l'event d'haver escollit una opció del menú contextual
     */
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.mnuEditar :
                llibEnviar = llibConv.getItemById(info.position);
                enviar = true;
//                NouLlibreActivity nllib = new NouLlibreActivity(llibConv.getItemById(info.position),true);
//                nllib.setEditar(true);
//               nllib.setLlibEditar(llibConv.getItemById(info.position));
//               System.out.println("dades :" + llibConv.getItemById(info.position).toString());
                Intent i = new Intent(this, NouLlibreActivity.class);
                i.putExtra("informacio",llibEnviar);
                i.putExtra("enviar", enviar);
                startActivityForResult(i, ADD_LLIBRE);
                return true;
            case R.id.mnuVeureDades:
                // mostrar les dades de l'element escollit
                //Toast.makeText(this, adapter.getItem(info.position).getTipus(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, llibConv.getItemById(info.position).getTipus(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuEsborrar:
                // esborrar l'element escollit
                llibConv.remove(adapter.getItem(info.position));
                // actualitzar la llista
                refreshData();
                // mostrar missatge
                Toast.makeText(this, "S'ha esborrat el llibre", Toast.LENGTH_LONG).show();
                return true;
            default: break;
        }
        return false;
    }
    public int getNumRegistres(){
        return numRegistres;
    }
    public void setNumRegistres(int numRegistres){
        this.numRegistres = numRegistres;
    }
    public boolean getEnviar(){
        return enviar;
    }
    public Llibres getLlibEnviar(){
        return llibEnviar;
    }
}