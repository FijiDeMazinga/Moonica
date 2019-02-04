package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Studente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SceltaFacolta extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner facolta, corsoDiStudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scelta_facolta);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra(Registrazione.USER);
        Studente s = (Studente) obj;

        facolta = findViewById(R.id.facolta);
        facolta.setOnItemSelectedListener(this);
        List<String> listaFacolta = new ArrayList<String>();
        listaFacolta.add("Facoltà");
        listaFacolta.add("Studi Umanistici");
        listaFacolta.add("Ingegneria e Architettura");
        listaFacolta.add("Scienze Economiche, Giuridiche e Politiche");
        listaFacolta.add("Medicina e Chirurgia");
        listaFacolta.add("Biologia e Farmacia");
        listaFacolta.add("Scienze");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, listaFacolta);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        facolta.setAdapter(dataAdapter);

        String facoltaScelta = facolta.getSelectedItem().toString();
        corsoDiStudi = findViewById(R.id.corso);
        corsoDiStudi.setOnItemSelectedListener(this);
        List<String> listaCorsi = new ArrayList<String>();
        listaCorsi.add("Corso");
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
        ArrayAdapter<String> dataAdapterCorsi = new ArrayAdapter<String>(this, R.layout.spinner_layout, listaCorsi);
        dataAdapterCorsi.setDropDownViewResource(R.layout.spinner_dropdown);
        corsoDiStudi.setAdapter(dataAdapterCorsi);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
