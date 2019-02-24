package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.ModificaSezioneAdapter;
import com.moonica.fdm.model.FactorySezioni;
import com.moonica.fdm.model.Professore;
import com.moonica.fdm.model.Sezione;
import com.moonica.fdm.model.SezioneAdapter;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Corsi extends AppCompatActivity {
    TextView professore, testoForum;
    CardView cv;
    Corso c;
    Utente utente;
    ScrollView sv;
    RecyclerView rv;
    ImageButton ib;
    ImageView freccia;
    Button indietro;
    ArrayList<Sezione> listaSezioni = new ArrayList<>();
    FactorySezioni factorySezioni = FactorySezioni.getInstance();
    DrawerLayout drawerLayout;

    public static final String FORUM = "com.moonica.fdm";
    static final String UTENTE  = "utente";
    public static final String CORSO = "com.moonica.fdm";
    public static final String NUOVASEZIONE = "nuovaSezione";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corsi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Serializable obj = null;
        Serializable objUtente = null;

        obj = i.getSerializableExtra("com.moonica.fdm");
        objUtente = i.getSerializableExtra("utente");

        c = (Corso) obj;
        utente = (Utente) objUtente;

        setTitle(c.getNome());
        listaSezioni = factorySezioni.getSezioniCorso(c.getNome());

        professore = findViewById(R.id.professore);
        if (c.getProfessore() == null)
            professore.setText("John Doe");
        else
            professore.setText(c.getProfessore().getNome().concat(" ").concat(c.getProfessore().getCognome()));

        sv = findViewById(R.id.sv_corsi);
        rv = findViewById(R.id.rvSezione);
        ib = findViewById(R.id.aggiungiSezione);
        //cancella = findViewById(R.id.cancellaSezione);
        freccia = findViewById(R.id.freccia);
        indietro = findViewById(R.id.indietroDaEliminaSezione);
        indietro.setVisibility(View.GONE);
        rv.setFocusable(false);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        cv = findViewById(R.id.forumCardView);
        testoForum = findViewById(R.id.testoForum);

        cv.setMinimumHeight(50);
        cv.setRadius(20);
        testoForum.setText("Forum " + c.getSigla());
        testoForum.setTextSize(18);
        testoForum.setPadding(40, 25, 0, 25);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Corsi.this, Forum.class);
                i.putExtra(FORUM, c);
                i.putExtra(UTENTE, utente);
                startActivity(i);
            }
        });

        if (utente instanceof Studente)
            ib.setVisibility(View.GONE);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuovaSezione = new Intent(Corsi.this, NuovaSezione.class);
                nuovaSezione.putExtra(NUOVASEZIONE, c);
                nuovaSezione.putExtra(UTENTE, utente);
                startActivityForResult(nuovaSezione, 1);
            }
        });

        //creazione navbar
        Intent intent = new Intent(Corsi.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(Corsi.this, Login.class);
        logOut(intentL);


        initializeAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (utente instanceof Professore)
            getMenuInflater().inflate(R.menu.opzioni_corso, menu);
        return true;
    }

    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.elimina_sezione:
                ModificaSezioneAdapter esa = new ModificaSezioneAdapter(c.getSezioni(), c);
                rv.setAdapter(esa);



                /*cancella.setVisibility(View.VISIBLE);
                freccia.setVisibility(View.GONE);*/



                indietro.setVisibility(View.VISIBLE);
                ib.setVisibility(View.GONE);



                //esa.notifyDataSetChanged();



                indietro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initializeAdapter();
                        finish();
                        startActivity(getIntent());
                        overridePendingTransition(0,0);
                    }
                });
                break;
            case R.id.elimina_tutte_le_sezioni:
                c.getSezioni().removeAll(c.getSezioni());
                finish();
                startActivity(getIntent());
                break;
            default:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                else {
                    FactorySezioni factorySezioni = FactorySezioni.getInstance();

                    ArrayList<Sezione> prova1 = factorySezioni.getSezioniCorso(c.getNome());

                    finish();
                }
        }
        return true;
    }

    public void setNavBar(final Intent intent){
        //menu
        ActionBarDrawerToggle actionBarDrawerToggle;
        NavigationView navigationView;
        //navMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.activityCorsi);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        navigationView = (NavigationView) findViewById(R.id.nv);
        CircleImageView avatar = new CircleImageView(this);
        TextView nomeUtente = new TextView(this);
        View header = navigationView.getHeaderView(0);
        avatar = header.findViewById(R.id.avatar);
        nomeUtente = header.findViewById(R.id.nomeUtente);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeNav:
                        intent.putExtra("com.moonica.fdm", utente);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });
        avatar.setImageResource(utente.getAvatar());
        nomeUtente.setText(utente.getNome() + " " + utente.getCognome());


        if (utente instanceof Studente) {

            aggiungiPreferiti(utente);
        }
        if (utente instanceof Professore){
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.preferiti).setVisible(false);


            TextView textView = findViewById(R.id.zeroCorsiPreferiti);
            textView.setVisibility(View.GONE);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Serializable strEditText = data.getSerializableExtra("nuova sezione");

                Sezione nuovaSezione = new Sezione();
                nuovaSezione = (Sezione) strEditText;


                ArrayList<Sezione> prova1 = c.getSezioni();
                //c.getSezioni().add(nuovaSezione);
                prova1.add(nuovaSezione);

                initializeAdapter();
            }
        }
    }

    private void initializeAdapter() {
        SezioneAdapter adapter = new SezioneAdapter(c.getSezioni(), utente);
        rv.setAdapter(adapter);
    }

    public void aggiungiPreferiti(final Utente u){
        NavigationView navigationView;
        navigationView = (NavigationView) findViewById(R.id.nv);

        LinearLayout linearLayout;
        linearLayout = navigationView.findViewById(R.id.listaPreferiti);

        TextView zeroPreferiti = navigationView.findViewById(R.id.zeroCorsiPreferiti);

        linearLayout.removeAllViews();

        if (u instanceof Studente) {
            int i=0;
            if (((Studente) u).getCorsiPreferiti().size() == 0){
                zeroPreferiti.setVisibility(View.VISIBLE);
            }
            else if (((Studente) u).getCorsiPreferiti().size() > 0)
                zeroPreferiti.setVisibility(View.GONE);

            for (Corso c : ((Studente) u).getCorsiPreferiti()) {

                final TextView textView = new TextView(this);
                textView.setText(c.getNome());
                textView.setPadding(50, 0, 0, 20);
                textView.setId(i+1);


                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Corsi.this, Corsi.class);

                        FactoryCorsi fc = FactoryCorsi.getInstance();
                        String name = String.valueOf(textView.getText());
                        String sigla = fc.cercaCorso(name).getSigla();

                        Corso corso = fc.cercaCorsoSigla(sigla);
                        intent.putExtra(CORSO, corso);
                        intent.putExtra(UTENTE, u);
                        startActivity(intent);
                    }
                });

                linearLayout.addView(textView);
                i++;
            }
        }



    }
}
