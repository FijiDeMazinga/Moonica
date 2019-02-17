package com.moonica.fdm.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

import java.io.Serializable;
import java.util.ArrayList;

public class Corsi extends AppCompatActivity {
    TextView professore;
    Corso c;
    //LinearLayout l;
    RecyclerView rv;
    ArrayList<Sezione> listaSezioni = new ArrayList<>();
    FactorySezioni factorySezioni = FactorySezioni.getInstance();

    public static final String FORUM = "com.moonica.fdm";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corsi);
        Intent i = getIntent();
        Serializable obj = null;

        obj = i.getSerializableExtra("com.moonica.fdm");

        c = (Corso) obj;
        setTitle(c.getNome());
        listaSezioni = factorySezioni.getSezioniCorso(c.getNome());

        professore = findViewById(R.id.professore);
        if (c.getProfessore() == null)
            professore.setText("John Doe");
        else
            professore.setText(c.getProfessore().getNome().concat(c.getProfessore().getCognome()));

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv = findViewById(R.id.rvSezione);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Button buttonForum = new Button(this);
        Space spaceForum = new Space(this);

        spaceForum.setMinimumHeight(50);
        buttonForum.setText("Forum " + c.getSigla());
        buttonForum.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        buttonForum.setPadding(30, 0, 30, 0);
        //buttonForum.setBackgroundResource(R.drawable.button_test);
        buttonForum.setTextColor(0xffffffff);
        buttonForum.setBackgroundColor(0xff225599);
        buttonForum.setTextSize(16);
        buttonForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forum = new Intent(Corsi.this, Forum.class);
                forum.putExtra(FORUM, c);
                startActivity(forum);
            }
        });

        //l.addView(buttonForum);
        //l.addView(spaceForum);
        rv.addView(buttonForum);
        rv.addView(spaceForum);

        SezioneAdapter sezioneAdapter = new SezioneAdapter(listaSezioni);
        rv.setAdapter(sezioneAdapter);

        for (final Sezione sezione : listaSezioni) {
            Button buttonSezione = new Button(this);
            Space spaceSezione = new Space(this);

            spaceSezione.setMinimumHeight(50);
            buttonSezione.setText(sezione.getTitolo());
            buttonSezione.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            buttonSezione.setPadding(30, 0, 30, 0);
            buttonSezione.setTextColor(0xffffffff);
            buttonSezione.setBackgroundColor(0xff225599);
            buttonSezione.setTextSize(16);

            //l.addView(buttonSezione);
            //l.addView(spaceSezione);
        }
    }
}
