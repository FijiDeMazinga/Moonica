package com.moonica.fdm.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.FileFinto;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewThread extends AppCompatActivity {


    Utente utente;
    Corso c = new Corso();
    ForumThread ft = new ForumThread();

    TextView titoloReply;

    EditText titolo, testo;
    ImageButton uploadFile;
    Button invio;


    public static final String FILE = "com.moonica.fdm";


    public static final String NEWTHREAD = "com.moonica.fdm";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        Serializable studObj = i.getSerializableExtra("utente");
        utente = (Utente) studObj;


        Bundle extras = i.getExtras();

        testo = (EditText) findViewById(R.id.testoNewT);
        uploadFile = (ImageButton) findViewById(R.id.caricaAllegato);
        invio = (Button) findViewById(R.id.inviaNT);



        if (extras.getString("newThread") != null){

            titolo = (EditText) findViewById(R.id.titoloNewT);

            c = (Corso) obj;


            invio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkTitolo()) {
                        String titleString = String.valueOf(titolo.getText());
                        String textString = String.valueOf(testo.getText());

                        FactoryUtente factoryUtente = FactoryUtente.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();
                        ForumThread forumThread = new ForumThread();

                        forumThread.setId(factoryForumThread.ultimoId());
                        forumThread.setTitolo(titleString);
                        forumThread.setTesto(textString);
                        forumThread.setCorso(c.getSigla(), c.getNome());
                        forumThread.setNumRisposte(0);
                        forumThread.setData(Calendar.getInstance());
                        forumThread.setAutore(factoryUtente.cercaUtente(utente.getUsername()));

                        factoryForumThread.aggiungiNuovoThread(forumThread);

                        Intent newThread = new Intent(NewThread.this, Thread.class);
                        newThread.putExtra("nuovoThread", "nuovoThread");
                        newThread.putExtra(NEWTHREAD, forumThread);
                        newThread.putExtra("utente", utente);
                        startActivity(newThread);

                    }
                }
            });

        }
        else if (extras.getString("longReply") != null){

            titoloReply = (TextView) findViewById(R.id.titoloNewT);

            ft = (ForumThread) obj;
            titoloReply.setText("Re:" + ft.getTitolo());
            titoloReply.setHintTextColor(Color.GRAY);
            titoloReply.setFocusable(false);
            titoloReply.setClickable(true);
            titoloReply.setTextColor(Color.GRAY);


            invio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkTesto()){
                        String textString = String.valueOf(testo.getText());

                        Commento commento = new Commento();

                        commento.setFt(ft.getId());
                        commento.setAutore(utente);
                        commento.setTesto(textString);
                        commento.setData(Calendar.getInstance());

                        FactoryCommenti factoryCommenti = FactoryCommenti.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();

                        factoryForumThread.aggiungiNumRisposte(ft.getId());
                        ft.setNumRisposte(factoryCommenti);
                        factoryCommenti.aggiungiCommentoLista(commento);

                        ArrayList<Commento> arrayList = new ArrayList<>();
                        arrayList.addAll(factoryCommenti.cercaListaCommenti(ft.getId()));

                        Intent intent = new Intent();
                        intent.putExtra("nuovo commento", ft);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
        //creazione navbar
        Intent intent = new Intent(NewThread.this, Home.class);
        setNavBar(intent);
        //funzione logout
        Intent intentL = new Intent(NewThread.this, Login.class);
        logOut(intentL);

    }

    public void UploadFile(View v) {

        Intent intent = new Intent(NewThread.this, CaricaFile.class);
        intent.putExtra(FILE, "nuovoThread");
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Serializable fileRicevuto = data.getSerializableExtra("allegato");

                FileFinto fileFinto = (FileFinto) fileRicevuto;


            }
        }
    }

    //funzione che stampa messaggi di errore se non vengono riempiti i campi
    public boolean checkTitolo(){
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if(titolo.getText() == null || titolo.getText().length() == 0){
            titolo.setError("Inserire un titolo");
            TextInputLayout til = findViewById(R.id.vibraTitolo);
            til.startAnimation(animation);
            errors++;
            hideKeyboard(this);
        }
        else
            titolo.setError(null);
        if(errors == 0) {
            return true;
        }
        return false;
    }

    public boolean checkTesto(){
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if(testo.getText() == null || testo.getText().length() == 0){
            testo.setError("Inserire una risposta");
            TextInputLayout til = findViewById(R.id.vibraTitolo);
            til.startAnimation(animation);
            errors++;
            hideKeyboard(this);
        }
        else
            testo.setError(null);
        if(errors == 0) {
            return true;
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
     * L'override chiude l'activity presente in cima allo stack
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
        drawerLayout = (DrawerLayout) findViewById(R.id.activityNewThread);
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
