package com.moonica.fdm.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.FactoryUtente;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ThreadRVAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class Thread extends AppCompatActivity {

    NestedScrollView ns;
    CardView cv;
    ForumThread ft;
    TextView titolo, testo, data, autore;
    FloatingActionButton fab;

    RecyclerView rv;

    ArrayList<Commento> commentsList = new ArrayList<>();
    FactoryCommenti fc = FactoryCommenti.getInstance();


    int IMAGE_PICKER_SELECT = 0;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        /*
         * Viene impostato il tasto per tornare alla activity precedente
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*
         * Vengono presi i dati inviati dall'activity precedente
         */
        final Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");

        ft = (ForumThread) obj;

        /*
         * Viene impostato il nome della pagina
         */
        setTitle(ft.getCorso().getNome() + " / Thread");

        commentsList = fc.cercaListaCommenti(ft.getId());

        /*
         * I dati del post principale del thread vengono caricati
         */
        ns = findViewById(R.id.scrollView_thread);
        cv = findViewById(R.id.cardView_main_post);
        titolo = findViewById(R.id.titoloThread_main_post);
        testo = findViewById(R.id.testoThread_main_post);
        data = findViewById(R.id.data_thread);
        autore = findViewById(R.id.autore_thread);
        fab = findViewById(R.id.fab_thread);

        titolo.setText(ft.getTitolo());
        testo.setText(ft.getTesto());
        data.setText(ft.getData().getTime().toGMTString());
        autore.setText(ft.getAutore().getNome() + " " + ft.getAutore().getCognome());

        rv = (RecyclerView) findViewById(R.id.rv_thread);

        rv.setFocusable(false);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();


        /*
         * Il FAB viene reso cliccabile
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReply(Thread.this);
            }
        });


        /*cv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });*/


        /*
         * Se la fine della NestedScrollView coincide alla fine dello schermo, il FAB viene reso trasparente
         * altrmenti viene reso senza nessuna trasparenza
         */

        if (ns != null) {
            ns.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (ns.getChildAt(0).getBottom()
                            <= (ns.getHeight() + ns.getScrollY())) {
                        fab.setAlpha(0.25f);
                    }
                    if (ns.getChildAt(0).getBottom()
                            > (ns.getHeight() + ns.getScrollY())) {
                        fab.setAlpha(0.99f);
                    }
                }
            });


        }


    }

    private void initializeAdapter() {
        ThreadRVAdapter adapter = new ThreadRVAdapter(commentsList);
        rv.setAdapter(adapter);

    }

    private void addReply(Context c) {

        final EditText reply = new EditText(c);
        final Button buttonPick = new Button(c);


        final AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Risposta")
                .setMessage("Aggiungi una risposta")
                //.setView(reply)
                .setPositiveButton("Rispondi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(reply.getText());

                        Commento newReply = new Commento();

                        FactoryUtente factoryUtente = FactoryUtente.getInstance();

                        newReply.setFt(ft.getId());
                        newReply.setTesto(task);
                        newReply.setData(Calendar.getInstance());
                        newReply.setAutore(factoryUtente.cercaUtente("Ines"));

                        FactoryCommenti factoryCommenti = FactoryCommenti.getInstance();
                        FactoryForumThread factoryForumThread = FactoryForumThread.getInstance();

                        factoryForumThread.aggiungiNumRisposte(ft.getId());
                        ft.setNumRisposte(factoryCommenti);
                        factoryCommenti.aggiungiCommentoLista(newReply);
                        commentsList.add(newReply);

                        ns.fullScroll(View.FOCUS_DOWN);

                        /*
                         * Quando aggiungo un commento al thread, devo aggiornare anche
                         * il thread stesso per numero di risposte.
                         */


                    }
                })
                .setNegativeButton("Annulla", null)
                .create();

        /*
         * Viene mostrata la tastiera per rispondere al thread
         */


        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        reply.setLayoutParams(lp);
        dialog.setView(reply);
        final AlertDialog dialog1 = dialog;
        dialog.show();

        ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE)
                .setEnabled(false);

        reply.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if edittext is empty
                if (TextUtils.isEmpty(s)) {
                    // Disable ok button
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY);

                } else {
                    // Something into edit text. Enable the button.
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    ((AlertDialog) dialog1).getButton(
                            AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);

                }

            }
        });


        /*dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

            }
        });*/


        dialog.show();

    }


    /*
     * L'ovveride chiude l'activity presente in cima allo stack
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}


