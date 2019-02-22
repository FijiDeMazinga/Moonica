package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Studente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SceltaFacolta extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner facolta, corsoDiStudi;
    public static final String USER = "com.moonica.fdm";

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_facolta);
        setTitle("Scelta facoltà");

        Intent i = getIntent();
        //ricevo lo studente dalla activity precedente
        Serializable obj = i.getSerializableExtra(Registrazione.USER);
        final Studente s = (Studente) obj;

        facolta = findViewById(R.id.facolta);
        b = findViewById(R.id.sceltafacolta);

        //dichiarazione di cosa succede ogni volta che seleziono un item nello spinner facolta
        facolta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //salvo il nome della facoltà in una stringa
                String fac = (String) parent.getItemAtPosition(position);
                //richiamo la funzione che popola lo spinner dei corsi in base alla facoltà scelta
                populateCorso(fac);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //aggiunta delle stringhe allo spinner facolta
        List<String> listaFacolta = new ArrayList<String>();
        listaFacolta.add("Facoltà");
        listaFacolta.add("Studi Umanistici");
        listaFacolta.add("Ingegneria e Architettura");
        listaFacolta.add("Scienze Economiche, Giuridiche e Politiche");
        listaFacolta.add("Medicina e Chirurgia");
        listaFacolta.add("Biologia e Farmacia");
        listaFacolta.add("Scienze");

        //adattatore dello spinner con la lista passata e lo stile scelto
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, listaFacolta);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        facolta.setAdapter(dataAdapter);//aggiunta dell'adapter allo spinner

        corsoDiStudi = findViewById(R.id.corso);
        corsoDiStudi.setOnItemSelectedListener(this);

        //dichiarazione di cosa succede quando premo il bottone
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(check()){//se i campi non sono vuoti richiamo l'activity successiva passando lo studente modificato
                Intent r = new Intent(SceltaFacolta.this, Home.class);
                r.putExtra(USER, aggiornaStudente(s));//aggiungo l'extra con lo studente modificato
                startActivity(r);
                finish();
            }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //funzione che popola lo spinner dei corsi
    public void populateCorso(String facoltaScelta){
        List<String> listaCorsi = new ArrayList<String>();
        listaCorsi.add("Corso");
        //a seconda della stringa passata aggiungo corsi diversi alla lista
        switch (facoltaScelta){
            case "Studi Umanistici":
                listaCorsi.add("Lettere");
                listaCorsi.add("Psicologia");
                listaCorsi.add("Scienze della formazione primaria");
                break;
            case "Ingegneria e Architettura":
                listaCorsi.add("Ingegneria Biomedica");
                listaCorsi.add("Ingegneria Civile");
                listaCorsi.add("Scienze dell'Architettura");
                break;
            case "Scienze Economiche, Giuridiche e Politiche":
                listaCorsi.add("Scienze Politiche");
                listaCorsi.add("Economia Manageriale");
                listaCorsi.add("Giurisprudenza");
                break;
            case "Medicina e Chirurgia":
                listaCorsi.add("Medicina e Chirurgia");
                listaCorsi.add("Infermieristica");
                listaCorsi.add("Ostetricia");
                break;
            case "Biologia e Farmacia":
                listaCorsi.add("Chimica e Tecnologie Farmaceutiche");
                listaCorsi.add("Biologia");
                listaCorsi.add("Tossicologia");
                break;
            case "Scienze":
                listaCorsi.add("Informatica");
                listaCorsi.add("Matematica");
                listaCorsi.add("Fisica");
                break;
        }
        //adapter per lo spinner
        ArrayAdapter<String> dataAdapterCorsi = new ArrayAdapter<String>(this, R.layout.spinner_layout, listaCorsi);
        dataAdapterCorsi.setDropDownViewResource(R.layout.spinner_dropdown);
        corsoDiStudi.setAdapter(dataAdapterCorsi);
    }
    //funzione che aggiunge facoltà e corso scelto allo studente
    public Studente aggiornaStudente(Studente s){
        s.setAnnoCorso(1);
        s.setFacolta(facolta.getSelectedItem().toString());
        s.setCorsoStudi(corsoDiStudi.getSelectedItem().toString());
        return s;
    }
    //funzione che controlla che i campi siano riempiti
    public boolean check(){
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_error);
        int errors = 0;
        if(facolta.getSelectedItem().toString().equals("Facoltà")) {
            SetError("Inserire facoltà", facolta, (TextView) findViewById(R.id.invisibleErrorF));
            facolta.startAnimation(animation);
            errors++;
        }
        if(corsoDiStudi.getSelectedItem().toString().equals("Corso")) {
            SetError("Inserire corso di studi", corsoDiStudi, (TextView) findViewById(R.id.invisibleErrorC));
            corsoDiStudi.startAnimation(animation);
            errors++;
        }
        if(errors == 0)
            return true;
        return false;
    }
    //funzione che aggiunge un messaggio di errore allo spinner
    public void SetError(String errorMessage, Spinner s, TextView textView){
        View view = s.getSelectedView();

        TextView t = (TextView) view;
        TextView error = (TextView) findViewById(R.id.invisibleError);
        if(errorMessage != null){
            t.setError(errorMessage);
            t.requestFocus();
        }
        else{
            t.setError(null);
            textView.setError(null);
        }
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
