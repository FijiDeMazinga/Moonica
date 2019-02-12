package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ForumRVAdapter;


import java.io.Serializable;
import java.util.ArrayList;


public class Forum extends AppCompatActivity {
    Corso c;
    RecyclerView rv;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Intent i = getIntent();
        Serializable obj = i.getSerializableExtra("com.moonica.fdm");
        c = (Corso) obj;

        setTitle(c.getNome() + " / Forum");

        /*
         * funzione per prendere i thread relativi ad uno specifico corso
         */
        listaForum = fft.cercaThreadCorso(c);

        rv=(RecyclerView)findViewById(R.id.rv_forum);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();

    }
    private void initializeAdapter() {
        ForumRVAdapter adapter = new ForumRVAdapter(listaForum);
        rv.setAdapter(adapter);

    }
}

