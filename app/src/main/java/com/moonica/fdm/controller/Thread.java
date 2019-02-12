package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.ForumThread;

import java.io.Serializable;
import java.util.ArrayList;


public class Thread extends AppCompatActivity {

    ForumThread ft;
    TextView titolo, testo, data, autore;
    ArrayList<Commento> commentsList = new ArrayList<>();
    FactoryCommenti fc = FactoryCommenti.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");

        ft = (ForumThread) obj;

        commentsList = fc.cercaListaCommenti(ft);

        titolo = findViewById(R.id.titoloThread_main_post);
        testo = findViewById(R.id.testoThread_main_post);
        data = findViewById(R.id.data_thread);
        autore = findViewById(R.id.autore_thread);

        titolo.setText(ft.getTitolo());
        testo.setText(ft.getTesto());
        data.setText("prova");
        autore.setText(ft.getAutore().getNome() + " " + ft.getAutore().getCognome());


    }
}
