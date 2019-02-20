package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ForumRVAdapter;
import com.moonica.fdm.model.LinearLayoutPagerManager;
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

        ns = (ScrollView)findViewById(R.id.sv_forum);
        rv = (RecyclerView) findViewById(R.id.rv_forum);


        /*
         * Inizializzazione dell'adapter
         */
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        initializeAdapter(utente);

        /*
         * Prova paging
         */
        pagingView();
        /*
         * Fine prova paging
         */

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
                Intent newThread = new Intent(Forum.this, NewThread.class);
                newThread.putExtra("newThread", "nuovo thread");
                newThread.putExtra(NEWTHREAD, c);
                newThread.putExtra("utente", utente);
                startActivity(newThread);
                break;
            default:
                finish();
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

    public void pagingView(){
        /*rv.setNestedScrollingEnabled(false);
        ns.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                View view = (View) ns.getChildAt(ns.getChildCount()-1);
                int diff = (view.getBottom() - (ns.getHeight() + ns.getScrollY()));

                if (diff == 0){
                    PagerSnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(rv);
                }
            }
        });*/

        /*LinearLayoutPagerManager linearLayoutPagerManager = new LinearLayoutPagerManager(this, LinearLayoutManager.VERTICAL, false, itemsperpage);



        rv.setLayoutManager(linearLayoutPagerManager);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);*/
    }
    public void setNavBar(final Intent intent){
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
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });
        avatar.setImageResource(utente.getAvatar());
        nomeUtente.setText(utente.getNome() + " " + utente.getCognome());

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

