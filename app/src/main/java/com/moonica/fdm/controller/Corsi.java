package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactorySezioni;
import com.moonica.fdm.model.Sezione;

import java.io.Serializable;
import java.util.ArrayList;

public class Corsi extends AppCompatActivity {
    Corso c;
    LinearLayout l;
    ArrayList<Sezione> listaSezioni = new ArrayList<>();
    FactorySezioni factorySezioni = FactorySezioni.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corsi);
        Intent i = getIntent();
        Serializable obj = null;

        obj = i.getSerializableExtra("com.moonica.fdm");

        c = (Corso) obj;
        setTitle(c.getNome());

        //aggiungere come sottotitolo il nome del professore che tiene il corso
    }
}
