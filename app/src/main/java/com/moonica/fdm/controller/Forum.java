package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
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
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;


public class Forum extends AppCompatActivity {

    TextView titolo;
    Corso c;
    LinearLayout l;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
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

            String[] date = ft.getData().getTime().toLocaleString().split(" ");

            String prova = getElapsedDaysText(ft.getData(), Calendar.getInstance());

            if(ft.getTitolo().length() <= 20)
                bt.setText("da " + ft.getAutore().getNome() + " " + ft.getAutore().getCognome() + "\n" +
                        ft.getTitolo());
            else
                bt.setText("da " + ft.getAutore().getNome() + " " + ft.getAutore().getCognome() + "\n" +
                        ft.getTitolo().substring(0, 20) + "..." + "\n" +
                        ft.getNumRisposte() + " risposte" + "\n" +
                        prova);

            bt.setMinHeight(600);

            bt.setTextColor(0xffffffff);
            bt.setBackgroundColor(0xff225599);
            bt.setTextSize(16);


            l.addView(bt);
            l.addView(s);

        }

    }

    public String getElapsedDaysText(Calendar c1, Calendar c2)
    {
        String elapsedDaysText = null;

            long milliSeconds1 = c1.getTimeInMillis();
            long milliSeconds2 = c2.getTimeInMillis();
            long periodSeconds = (milliSeconds2 - milliSeconds1) / 1000;
            long elapsedDays = periodSeconds / 60 / 60 / 24;
            if (elapsedDays < 1 && periodSeconds < 60)
                elapsedDaysText = "meno di un minuto fa";
            else if (elapsedDays < 1 && periodSeconds < 3600)
                elapsedDaysText = periodSeconds*60 + " minuti fa";
            else if (elapsedDays < 1 && periodSeconds < 7200)
                elapsedDaysText = (periodSeconds*3600) + " ora fa";
            else if (elapsedDays < 1 && periodSeconds >= 7200)
                elapsedDaysText = (periodSeconds*3600) + " ore fa";
            else if (elapsedDays < 2)
                elapsedDaysText = elapsedDays + " giorno fa";
            else if (elapsedDays < 30)
                elapsedDaysText = elapsedDays + " giorni fa";
            else if (elapsedDays < 60)
                elapsedDaysText = elapsedDays/30 + " mese fa";
            else if (elapsedDays < 365)
                elapsedDaysText = (elapsedDays/30) + " mesi fa";
            else if (elapsedDays < 730)
                elapsedDaysText = (elapsedDays/365) + " anno fa";
            else
                elapsedDaysText = (elapsedDays/365) + " anni fa";

        return elapsedDaysText;
    }
}
