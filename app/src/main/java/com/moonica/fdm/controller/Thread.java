package com.moonica.fdm.controller;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Commento;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ForumRVAdapter;
import com.moonica.fdm.model.ThreadRVAdapter;

import java.io.Serializable;
import java.util.ArrayList;


public class Thread extends AppCompatActivity {

    CardView cv;
    ForumThread ft;
    TextView titolo, testo, data, autore;

    RecyclerView rv;

    ArrayList<Commento> commentsList = new ArrayList<>();
    FactoryCommenti fc = FactoryCommenti.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");

        ft = (ForumThread) obj;

        setTitle(ft.getCorso().getNome() + " / Thread");

        commentsList = fc.cercaListaCommenti(ft.getId());

        /*
         * I dati del post principale del thread vengono caricati
         */

        cv = findViewById(R.id.cardView_main_post);
        titolo = findViewById(R.id.titoloThread_main_post);
        testo = findViewById(R.id.testoThread_main_post);
        data = findViewById(R.id.data_thread);
        autore = findViewById(R.id.autore_thread);

        titolo.setText(ft.getTitolo());
        testo.setText(ft.getTesto());
        data.setText(ft.getData().getTime().toGMTString());
        autore.setText(ft.getAutore().getNome() + " " + ft.getAutore().getCognome());

        rv = (RecyclerView)findViewById(R.id.rv_thread);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();



        /*cv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });*/

    }

    private void initializeAdapter() {
        ThreadRVAdapter adapter = new ThreadRVAdapter(commentsList);
        rv.setAdapter(adapter);

    }


}
