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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ForumRVAdapter;
import com.moonica.fdm.model.Professore;
import com.moonica.fdm.model.Studente;
import com.moonica.fdm.model.Utente;
import java.io.Serializable;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class Forum extends AppCompatActivity {

    ScrollView ns;
    Corso c;
    static Utente utente;
    RecyclerView rv;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();

    static final String UTENTE = "utente";
    public static final String CORSO = "com.moonica.fdm";
    public static final String NEWTHREAD = "com.moonica.fdm";
    private DrawerLayout drawerLayout;

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        Serializable studObj = i.getSerializableExtra("utente");
        c = (Corso) obj;
        utente = (Utente) studObj;

        setTitle(c.getNome() + " / Forum");

        /*
         * funzione per prendere i thread relativi ad uno specifico corso
         */
        listaForum = fft.cercaThreadCorso(c);

        ns = (ScrollView) findViewById(R.id.sv_forum);
        rv = (RecyclerView) findViewById(R.id.rv_forum);

        /*
         * Inizializzazione dell'adapter
         */
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter(utente);

        //creazione navbar
        Intent intent = new Intent(Forum.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(Forum.this, Login.class);
        logOut(intentL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newthread, menu);
        return true;
    }

    private void initializeAdapter(Utente u) {
        ForumRVAdapter adapter = new ForumRVAdapter(listaForum, u);
        rv.setAdapter(adapter);
    }

    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_newThread:

                //Intent newThread = new Intent("android.media.action.IMAGE_CAPTURE");
                Intent newThread = new Intent(Forum.this, NewThread.class);
                newThread.putExtra("newThread", "nuovo thread");
                newThread.putExtra(NEWTHREAD, c);
                newThread.putExtra("utente", utente);
                startActivity(newThread);
                break;
            default:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {

                    finish();
                }
        }
        return true;
    }

    /*
     * Se l'utente arriva dall'activity di un thread, la pagina viene aggiornata
     */

    @Override
    public void onResume() {
        super.onResume();

        initializeAdapter(utente);
    }

    public void setNavBar(final Intent intent) {
        //menu
        ActionBarDrawerToggle actionBarDrawerToggle;
        NavigationView navigationView;
        //navMenu
        drawerLayout = (DrawerLayout) findViewById(R.id.forumActivity);
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
                        overridePendingTransition(0, 0);
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

    public void logOut(final Intent intent) {
        RelativeLayout relativeLayout = findViewById(R.id.buttonLogOut);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }

    public void aggiungiPreferiti(final Utente u) {
        NavigationView navigationView;
        navigationView = (NavigationView) findViewById(R.id.nv);

        LinearLayout linearLayout;
        linearLayout = navigationView.findViewById(R.id.listaPreferiti);

        TextView zeroPreferiti = navigationView.findViewById(R.id.zeroCorsiPreferiti);

        linearLayout.removeAllViews();

        if (u instanceof Studente) {
            int i = 0;
            if (((Studente) u).getCorsiPreferiti().size() == 0) {
                zeroPreferiti.setVisibility(View.VISIBLE);
            } else if (((Studente) u).getCorsiPreferiti().size() > 0)
                zeroPreferiti.setVisibility(View.GONE);

            for (Corso c : ((Studente) u).getCorsiPreferiti()) {

                final TextView textView = new TextView(this);
                textView.setText(c.getNome());
                textView.setPadding(50, 0, 0, 20);
                textView.setId(i + 1);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Forum.this, Corsi.class);

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

