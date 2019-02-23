package com.moonica.fdm.controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.HomeRVAAdapter;
import com.moonica.fdm.model.ItemMoveCallback;
import com.moonica.fdm.model.Professore;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {
    TextView welcome;
    Utente u;
    Professore p;
    Studente s;
    String username, benvenuto;
    RecyclerView rv;
    ArrayList<Corso> lista = new ArrayList<>();
    FactoryCorsi fc = FactoryCorsi.getInstance();
    Dialog scelta;
    DrawerLayout drawerLayout;

    public static final String CORSO = "com.moonica.fdm";

    @SuppressLint({"ResourceType", "WrongViewCast"})
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle("I tuoi corsi");//titolo che comparirà nell'actionbar
        Intent i = getIntent();
        Serializable obj;
        scelta = new Dialog(this);//inizializzazione Dialog per il pop-up

        //prelievo attributo tramite l'intent
        obj= i.getSerializableExtra("com.moonica.fdm");
        u = (Utente) obj;

        //cambio il messaggio di benvenuto in base al sesso dell'utente
        switch (u.getSesso()){
            case FEMMINA:
                benvenuto = "Benvenuta ";
                break;
            case MASCHIO:
                benvenuto = "Benvenuto ";
                break;
            case NONSPECIFICATO:
                benvenuto = "Benvenut* ";
                break;
        }
        //in base al tipo di utente cambio il metodo per ottenere la lista dei corsi
        if(obj instanceof Studente){
            s = (Studente) obj;
            username = s.getUsername();
            lista = s.getCorsi();
        }
        else{
            p = (Professore) obj;
            username = p.getUsername();
            lista = p.getCorsiGestiti();
            ImageButton plus = (ImageButton) findViewById(R.id.plus);
            plus.setVisibility(View.INVISIBLE);//se l'utente è un professore rimuovo il pulsante per aggiungere i corsi
        }
        //creazione navbar
        Intent intent = new Intent(Home.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(Home.this, Login.class);
        logOut(intentL);

        //settaggio del messaggio di benvenuto
        welcome = findViewById(R.id.welcome);
        welcome.setText(benvenuto.concat(username).concat("!"));

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv = findViewById(R.id.rvCorso);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        HomeRVAAdapter hra = new HomeRVAAdapter(lista, s, u, p);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(hra);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);

        rv.setAdapter(hra);
    }
    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    //funzione che parte alla pressione del pulsante di aggiunta di un corso
    public void ShowPopup(View v){
        LayoutInflater f = getLayoutInflater();
        //questa sarà la vista che verrà mostrata nel caso di pressione del pulsante
        View view =  f.inflate(R.layout.activity_aggiungi_corso, null);//richiama l'activity_aggiungi_corso

        //dichiarazione gridlayout(da sistemare graficamente)
        final LinearLayout gridLayout = (LinearLayout) view.findViewById(R.id.griglia);

        //se il corso di studi dell'utente esiste
        if(s.getCorsoStudi() != null) {
            //prendo i corsi della facoltà a cui è iscritto l'utente

            ArrayList<Corso> listaNuovi = new ArrayList<Corso>();
            listaNuovi = fc.listaCorsiFacolta(s.getCorsoStudi().getNome());
            //rimuovo quelli a cui è già iscritto
            listaNuovi.removeAll(s.getCorsi());
            if(listaNuovi.size() > 0) {
                FactoryCorsi fc = FactoryCorsi.getInstance();

                //per ogni corso aggiungo un bottone
                for (final Corso c : listaNuovi) {
                    final Button b = new Button(this);
                    Space space = new Space(this);
                    space.setMinimumHeight(5);
                    //settaggio parametri bottone
                    b.setText(c.getNome());
                    b.setBackgroundColor(0xff225599);
                    b.setGravity(Gravity.CENTER_HORIZONTAL);
                    b.setMinimumWidth(900);
                    b.setTextColor(0xffeeeeee);
                    //dichiarazione di cosa succede cliccando il bottone
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FactoryCorsi fc = FactoryCorsi.getInstance();
                            s.aggiungiCorso(fc.cercaCorso(c.getNome()));//viene aggiunto il corso corrispondente
                            scelta.dismiss();//viene chiuso il pop-up
                            //viene refreshata l'activity home
                            finish();
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                    });
                    gridLayout.addView(space);
                    gridLayout.addView(b);//aggiunta dei bottoni al layout
                }
                scelta.setContentView(view);//setting della view da visualizzare col pop-up
                scelta.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                scelta.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                scelta.show();//lancio del pop-up
            }
            else{
                Toast t = Toast.makeText(this, "Non ci sono altri corsi disponibili", Toast.LENGTH_SHORT);
                t.show();
            }
        }
        //se il corso di studi non esiste visualizzo un messaggio di errore tramite Toast
        else {
            Toast t = Toast.makeText(this, "Non ci sono corsi disponibili per la tua facoltà", Toast.LENGTH_SHORT);
            t.show();
        }
    }


    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            default:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void setNavBar(final Intent intent){
        //menu
        ActionBarDrawerToggle actionBarDrawerToggle;
        NavigationView navigationView;
        LinearLayout linearLayout;
        //navMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.activityHome);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nv);
        CircleImageView avatar = new CircleImageView(this);
        TextView nomeUtente = new TextView(this);
        View header = navigationView.getHeaderView(0);
        avatar = header.findViewById(R.id.avatar);
        nomeUtente = header.findViewById(R.id.nomeUtente);
        linearLayout = navigationView.findViewById(R.id.listaPreferiti);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeNav:
                        intent.putExtra("com.moonica.fdm", u);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });
        avatar.setImageResource(u.getAvatar());
        nomeUtente.setText(u.getNome() + " " + u.getCognome());

        ArrayList<Corso> preferiti = new ArrayList<>();
        preferiti.addAll(s.getCorsiPreferiti());
        for(Corso c : preferiti){
            TextView textView = new TextView(this);
            textView.setText(c.getSigla());
            linearLayout.addView(textView);
        }
    }

    public void logOut(final Intent intent){
        RelativeLayout relativeLayout = findViewById(R.id.buttonLogOut);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }
}


