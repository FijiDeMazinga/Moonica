package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.moonica.fdm.R;
import com.moonica.fdm.model.Corso;
import com.moonica.fdm.model.FactorySezioni;
import com.moonica.fdm.model.ForumThread;
import com.moonica.fdm.model.ItemMoveCallback;
import com.moonica.fdm.model.Sezione;
import com.moonica.fdm.model.SezioneAdapter;
import com.moonica.fdm.model.Utente;

import java.io.Serializable;
import java.util.ArrayList;

public class Corsi extends AppCompatActivity {
    TextView professore, testoForum;
    CardView cv;
    Corso c;
    Utente utente;
    //LinearLayout l;
    RecyclerView rv;
    ArrayList<Sezione> listaSezioni = new ArrayList<>();
    FactorySezioni factorySezioni = FactorySezioni.getInstance();

    public static final String FORUM = "com.moonica.fdm";
    static final String UTENTE  = "utente";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corsi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Serializable obj = null;
        Serializable objUtente = null;

        obj = i.getSerializableExtra("com.moonica.fdm");
        objUtente = i.getSerializableExtra("utente");


        c = (Corso) obj;
        utente = (Utente) objUtente;

        setTitle(c.getNome());
        listaSezioni = factorySezioni.getSezioniCorso(c.getNome());

        professore = findViewById(R.id.professore);
        if (c.getProfessore() == null)
            professore.setText("John Doe");
        else
            professore.setText(c.getProfessore().getNome().concat(c.getProfessore().getCognome()));

        rv = findViewById(R.id.rvSezione);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        //l.addView(buttonForum);
        //l.addView(spaceForum);
        //rv.addView(buttonForum);
        //rv.addView(spaceForum);

        cv = findViewById(R.id.forumCardView);
        testoForum = findViewById(R.id.testoForum);

        cv.setMinimumHeight(50);
        cv.setRadius(20);
        testoForum.setText("Forum " + c.getSigla());
        testoForum.setTextSize(18);
        testoForum.setPadding(40, 25, 0, 25);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Corsi.this, Forum.class);
                i.putExtra(FORUM, c);
                i.putExtra(UTENTE, utente);
                startActivity(i);
            }
        });

        SezioneAdapter sezioneAdapter = new SezioneAdapter(listaSezioni, utente);
        rv.setAdapter(sezioneAdapter);
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
