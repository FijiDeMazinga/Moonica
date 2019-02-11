package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import org.w3c.dom.Text;

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
    CardView cv;
    ArrayList<ForumThread> listaForum = new ArrayList<>();
    FactoryForumThread fft = FactoryForumThread.getInstance();

    public static final String THREAD = "com.moonica.fdm";

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Intent i = getIntent();


        Serializable obj = i.getSerializableExtra("com.moonica.fdm");

        c = (Corso) obj;
        /*
         * funzione per prendere i thread relativi ad uno specifico corso
         */
        listaForum = fft.cercaThreadCorso(c);

        titolo = findViewById(R.id.forumWelcome);
        titolo.setText("Forum di " + c.getNome());


        l = findViewById(R.id.threadButton);
        cv = findViewById(R.id.card_view);


        /*
         * Prove
         */


        for (ForumThread ft : listaForum) {

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.rv);

            TextView autore = (TextView) findViewById(R.id.autore);
            TextView titolo = (TextView) findViewById(R.id.titoloThread);
            TextView numR = (TextView) findViewById(R.id.numRisposte);
            TextView data = (TextView) findViewById(R.id.data);

            Space s = new Space(this);

            s.setMinimumHeight(50);

            String dateFormat = getElapsedDaysText(ft.getData(), Calendar.getInstance());
            data.setText(dateFormat);
            autore.setText("di " + ft.getAutore().getNome() + " " + ft.getAutore().getCognome());
            titolo.setText(ft.getTitolo());
            numR.setText(ft.getNumRisposte() + " risposte");

            if (autore.getParent() != null)
                ((ViewGroup) autore.getParent()).removeView(autore);
            rl.addView(autore);
            if (titolo.getParent() != null)
                ((ViewGroup) titolo.getParent()).removeView(titolo);
            rl.addView(titolo);
            if (numR.getParent() != null)
                ((ViewGroup) numR.getParent()).removeView(numR);
            rl.addView(numR);
            if (data.getParent() != null)
                ((ViewGroup) data.getParent()).removeView(data);
            rl.addView(data);



         //   l.addView(s);
        }

        /*
         * Fine Prove
         */
/*
        for (final ForumThread ft : listaForum) {
            TextView autore;
            Button bt = new Button(this);
            Space s = new Space(this);

            s.setMinimumHeight(50);

            String dateFormat = getElapsedDaysText(ft.getData(), Calendar.getInstance());

            if (ft.getTitolo().length() <= 20)
                bt.setText("da " + ft.getAutore().getNome() + " " + ft.getAutore().getCognome() + "\n" +
                        ft.getTitolo() + "..." + "\n" +
                        ft.getNumRisposte() + " risposte" + "\n" +
                        dateFormat);
            else
                bt.setText("da " + ft.getAutore().getNome() + " " + ft.getAutore().getCognome() + "\n" +
                        ft.getTitolo().substring(0, 20) + "..." + "\n" +
                        ft.getNumRisposte() + " risposte" + "\n" +
                        dateFormat);


            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent thread = new Intent(Forum.this, Thread.class);
                    thread.putExtra(THREAD, ft);
                    startActivity(thread);
                }
            });

            bt.setMinHeight(600);

            bt.setTextColor(0xffffffff);
            bt.setBackgroundColor(0xff225599);
            bt.setTextSize(16);


            l.addView(bt);
            l.addView(s);

        }
*/
    }

    public String getElapsedDaysText(Calendar c1, Calendar c2) {
        String elapsedDaysText = null;

        long milliSeconds1 = c1.getTimeInMillis();
        long milliSeconds2 = c2.getTimeInMillis();
        long periodSeconds = (milliSeconds2 - milliSeconds1) / 1000;
        long elapsedDays = periodSeconds / 60 / 60 / 24;
        if (elapsedDays < 1 && periodSeconds < 60)
            elapsedDaysText = "meno di un minuto fa";
        else if (elapsedDays < 1 && periodSeconds < 3600)
            elapsedDaysText = periodSeconds * 60 + " minuti fa";
        else if (elapsedDays < 1 && periodSeconds < 7200)
            elapsedDaysText = (periodSeconds * 3600) + " ora fa";
        else if (elapsedDays < 1 && periodSeconds >= 7200)
            elapsedDaysText = (periodSeconds * 3600) + " ore fa";
        else if (elapsedDays < 2)
            elapsedDaysText = elapsedDays + " giorno fa";
        else if (elapsedDays < 30)
            elapsedDaysText = elapsedDays + " giorni fa";
        else if (elapsedDays < 60)
            elapsedDaysText = elapsedDays / 30 + " mese fa";
        else if (elapsedDays < 365)
            elapsedDaysText = (elapsedDays / 30) + " mesi fa";
        else if (elapsedDays < 730)
            elapsedDaysText = (elapsedDays / 365) + " anno fa";
        else
            elapsedDaysText = (elapsedDays / 365) + " anni fa";

        return elapsedDaysText;
    }
}
