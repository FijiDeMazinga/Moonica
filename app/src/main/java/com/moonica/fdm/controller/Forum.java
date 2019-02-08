package com.moonica.fdm.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.CorsoDiStudi;
import com.moonica.fdm.model.FactoryCommenti;
import com.moonica.fdm.model.FactoryCorsi;
import com.moonica.fdm.model.FactoryCorsoDiStudi;
import com.moonica.fdm.model.FactoryForumThread;
import com.moonica.fdm.model.ForumThread;

import java.io.Serializable;
import java.util.ArrayList;


public class Forum extends AppCompatActivity {

    TextView titolo;
    Corso c;
    LinearLayout l;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

       //Intent i = getIntent();


        //Serializable obj = i.getSerializableExtra(Corso.cs);


        //Da togliere
        c = FactoryCorsi.getInstance().cercaCorso("Diritto Privato");


        //Fine togliere

        //c = (Corso) obj;
        /*
         * funzione per prendere i thread relativi ad uno specifico corso
         */
        listaForum = fft.cercaThreadCorso(c);

        titolo = findViewById(R.id.forumWelcome);
        titolo.setText("Forum di " + c.getNome());

        l = findViewById(R.id.threadButton);

        for (ForumThread ft : listaForum){
            TextView autore;
            Button bt = new Button(this);
            Space s = new Space(this);

            s.setMinimumHeight(50);

            if(ft.getTitolo().length() <= 20)
                bt.setText(ft.getTitolo());
            else
                bt.setText(ft.getTitolo().substring(0, 20) + "...");



            bt.setTextColor(0xffffffff);
            bt.setBackgroundColor(0xff225599);
            bt.setTextSize(16);


            l.addView(bt);
            l.addView(s);

        }

    }
}
