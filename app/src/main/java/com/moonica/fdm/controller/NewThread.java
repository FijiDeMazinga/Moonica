package com.moonica.fdm.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.Calendar;

public class NewThread extends AppCompatActivity {


    Utente utente;
    Corso c = new Corso();
    ForumThread ft = new ForumThread();

    TextView titoloReply;

    EditText titolo, testo;
    ImageButton uploadFile;
    Button invio;


    public static final String NEWTHREAD = "com.moonica.fdm";

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
        testo = (EditText) findViewById(R.id.testoNewT);
        uploadFile = (ImageButton) findViewById(R.id.caricaAllegato);
        invio = (Button) findViewById(R.id.inviaNT);


        if (i.getSerializableExtra("newThread").equals("nuovo thread")){

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
        else if (i.getSerializableExtra("reply").equals("risposta")){

            titoloReply = (TextView) findViewById(R.id.titoloNewT);

            ft = (ForumThread) obj;
            titolo.setFocusable(false);
            titolo.setClickable(true);
            titolo.setTextColor(Color.GRAY);


            invio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkTesto()){

                    }
                }
            });
        }

    }

    //funzione che stampa messaggi di errore se non vengono riempiti i campi
    public boolean checkTitolo(){
        int errors = 0;
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);

        if(titolo.getText() == null || titolo.getText().length() == 0){
            titolo.setError("Inserire un titolo");
            titolo.startAnimation(animation);
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
            testo.startAnimation(animation);
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
        finish();
        return true;
    }
}
